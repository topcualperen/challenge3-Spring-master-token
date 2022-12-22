package com.topcualperen.business;

import com.topcualperen.dataAccess.CategoryDao;
import com.topcualperen.dto.CategoryDto;
import com.topcualperen.entities.Category;
import com.topcualperen.exception.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryManager implements CategoryService{

    private CategoryDao categoryDao;

    @Autowired
    public CategoryManager(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto add(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryDao.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(int categoryId) {
        Optional<Category> category = categoryDao.findById(categoryId);
        if(category.isPresent()){
//            return product.get(); Entity den aldığımız ve ürünleri döndüğümüz işlem
            return modelMapper.map(category.get(), CategoryDto.class);
        }
        throw new ProductNotFoundException("Not found !!!");
    }

    @Override
    public CategoryDto updateCategory(int categoryId, CategoryDto category) {
        Optional<Category> updateCategoryy = categoryDao.findById(categoryId);
        if(updateCategoryy.isPresent()){
            updateCategoryy.get().setCategoryName(category.getCategoryName());
            return modelMapper.map(categoryDao.save(updateCategoryy.get()), CategoryDto.class);
        }
        throw new ProductNotFoundException("Not update !!!");
    }

    @Override
    public Boolean deleteCategory(int categoryId) {
        Optional<Category> category = categoryDao.findById(categoryId);
        if(category.isPresent()){
            categoryDao.deleteById(categoryId);
            return true;
        }
        return false;
    }

}

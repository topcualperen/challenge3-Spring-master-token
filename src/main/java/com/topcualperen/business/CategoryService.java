package com.topcualperen.business;


import com.topcualperen.dto.CategoryDto;


public interface CategoryService {

    CategoryDto add(CategoryDto category);

    CategoryDto getCategory(int categoryId);

    CategoryDto updateCategory(int categoryId,CategoryDto category);

    Boolean deleteCategory(int id);

}

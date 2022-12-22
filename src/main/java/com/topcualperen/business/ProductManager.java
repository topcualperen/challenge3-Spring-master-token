package com.topcualperen.business;

import com.topcualperen.core.utilities.results.*;
import com.topcualperen.dataAccess.ProductDao;
import com.topcualperen.dto.ProductCategoryDto;
import com.topcualperen.dto.ProductDto;
import com.topcualperen.entities.Product;
import com.topcualperen.exception.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductManager implements ProductService{

    private ProductDao productDao; // değişken

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDto add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return modelMapper.map(productDao.save(product), ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productDao.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
        return productDtos;
    }

    @Override
    public ProductDto getProduct(int id) {
        Optional<Product> product = productDao.findById(id); // Dao ya enitity den gidiyoruz
        if(product.isPresent()){
//            return product.get(); Entity den aldığımız ve ürünleri döndüğümüz işlem
            return modelMapper.map(product.get(), ProductDto.class);
        }
        throw new ProductNotFoundException("Product Not Found !!!");
    }

    @Override
    public ProductDto updateProduct(int id, ProductDto product) {
        Optional<Product> updateProductt = productDao.findById(id);
        if(updateProductt.isPresent()){
//            updateProductt.get().setId(product.getId());
            updateProductt.get().setProductName(product.getProductName());
            updateProductt.get().setUnitPrice(product.getUnitPrice());
//            updateProductt.get().setUnitsInStock(product.getUnitsInStock()); // ProductDto sınıfında sadece name ve price aldığımız için hata veriyor daha önceden entity den aldığımız için ve o sınıfda bu değişkenler olduğu için hata vermemişti.
//            updateProductt.get().setQuantityPerUnit(product.getQuantityPerUnit());
            return modelMapper.map(productDao.save(updateProductt.get()), ProductDto.class);
        }
        throw new ProductNotFoundException("Product Not Update !!!");
    }


    public void deleteProduct(int id) {
        Optional<Product> product = productDao.findById(id);
        if (product.isPresent()) {
            productDao.deleteById(id);
        } else{
            throw new ProductNotFoundException("Product Not Delete !!!");
        }
    }

//    @Override
//    public Boolean deleteProduct(int id) {
//        Optional<Product> product = productDao.findById(id);
//        if(product.isPresent()){
//            productDao.deleteById(id);
//            return true;
//        }
//        return false;
//    }

    @Override
    public DataResult<List<ProductCategoryDto>> getProductCategoryDetails() {
        return new SuccessDataResult<List<ProductCategoryDto>>
                (this.productDao.getProductCategoryDetails(), "Dto Başarılı.");
    }

}

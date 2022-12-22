package com.topcualperen.business;

import com.topcualperen.core.utilities.results.DataResult;
import com.topcualperen.dto.ProductCategoryDto;
import com.topcualperen.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto add(ProductDto product);

    List<ProductDto> getAll();

    ProductDto getProduct(int id);

    ProductDto updateProduct(int id,ProductDto product);

    void deleteProduct(int id);

    DataResult<List<ProductCategoryDto>> getProductCategoryDetails();

}

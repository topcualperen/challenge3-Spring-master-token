package com.topcualperen.api.controllers;

import com.topcualperen.business.ProductService;
import com.topcualperen.core.utilities.results.DataResult;
import com.topcualperen.dto.ProductCategoryDto;
import com.topcualperen.dto.ProductDto;
import com.topcualperen.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> add(@RequestBody ProductDto product){ // RespoonseEntity<Product>  -> ResponseEntity<ProductDao>
        ProductDto resultProduct = productService.add(product);
        return ResponseEntity.ok(resultProduct);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProductDto>> getAll(){
        List<ProductDto> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id")int id){
        // dönen değeri product nesnesinide tutuyoruz.
        ProductDto product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id")int id,@RequestBody ProductDto product){
        ProductDto updateProductt = productService.updateProduct(id,product);
        return ResponseEntity.ok(updateProductt);
    }


    @DeleteMapping("/delete/{product_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("product_id") int id) {
        productService.deleteProduct(id);
    }

//    @DeleteMapping("/delete/{product_id}")
//    public ResponseEntity<Boolean> deleteProduct(@PathVariable("product_id") int id){
//        Boolean isTrue = productService.deleteProduct(id);
//        return ResponseEntity.ok(isTrue);
//    }

    @GetMapping("/ProductCategoryDto")
    public DataResult<List<ProductCategoryDto>> getProductCategoryDetails() {
        return this.productService.getProductCategoryDetails();
    }


//    @ExceptionHandler({ProductNotFoundException.class})
//    public String productNotFoundId(){
//        return "Not found !!!";
//    }

}
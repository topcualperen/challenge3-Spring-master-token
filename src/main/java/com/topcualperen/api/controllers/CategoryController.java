package com.topcualperen.api.controllers;

import com.topcualperen.business.CategoryService;
import com.topcualperen.dto.CategoryDto;
import com.topcualperen.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryDto> add(@RequestBody CategoryDto category){
        CategoryDto resultCategory = categoryService.add(category);
        return ResponseEntity.ok(resultCategory);
    }

    @GetMapping("/getCategory/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id")int categoryId){
        // dönen değeri product nesnesinide tutuyoruz.
        CategoryDto category = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id")int categoryId,@RequestBody CategoryDto category){
        CategoryDto updatecategoryy = categoryService.updateCategory(categoryId,category);
        return ResponseEntity.ok(updatecategoryy);
    }

    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("category_id") int categoryId){
        Boolean isTrue = categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(isTrue);
    }



    @ExceptionHandler({CategoryNotFoundException.class})
    public String categoryNotFoundId(){
        return "Not found !!!";
    }


}

package com.topcualperen.dataAccess;

import com.topcualperen.dto.ProductCategoryDto;
import com.topcualperen.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {


    // JPQL (inner join ile tablo birleştirdik.)
    @Query("Select new com.topcualperen.dto.ProductCategoryDto" // mapping yaptık
            + "(p.id, p.productName, c.categoryName) "
            + "From Category c Inner Join c.products p") // one to many
    List<ProductCategoryDto> getProductCategoryDetails();


}

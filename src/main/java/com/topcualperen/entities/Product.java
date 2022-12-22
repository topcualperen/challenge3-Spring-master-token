package com.topcualperen.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity // veri tabanı nesnesi olduğunu söyledik
@Data // lombok(getter-setter)
@NoArgsConstructor // Constructor yazmamak için kullandık
@AllArgsConstructor
@Table(name = "products") // hangi tabloya karşılık geldiğini belirttik
public class Product {

    @Id // primary key olduğu için işlemleri id ye göre yapacak
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id nin nasıl oluşacağını söyler (birer birer)
    @Column(name = "product_id") // hangi kolona karşılık geldiğini belirttik
    private int id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "units_in_stock")
    private short unitsInStock;

    @Column(name = "quantity_per_unit")
    private String quantityPerUnit;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

}

package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products extends PanacheEntity {
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Column(name = "product_id")
    private int product_id;

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    @Column(name = "product_name")
    private String nameOfProduct;



    @Column (name = "product_price")
    private int product_price;

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }



    @Column (name = "product_owner")
    private String product_owner;

    public String getProduct_owner() {
        return product_owner;
    }

    public void setProduct_owner(String product_owner) {
        this.product_owner = product_owner;
    }

    public Products (int id, String nameOfProduct, int product_price, String product_owner){
        this.product_id = id;
        this.nameOfProduct = nameOfProduct;
        this.product_price = product_price;
        this.product_owner = product_owner;
    }

    public Products() {
    }
}

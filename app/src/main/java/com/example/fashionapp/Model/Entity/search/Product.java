package com.example.fashionapp.Model.Entity.search;

import com.google.gson.annotations.SerializedName;
import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class Product {
    @PropertyElement(name = "ProductCode")
    private String productCode;
    @PropertyElement(name="ProductName")
    private String productName;
    @PropertyElement(name="ProductImage300")
    private String productImage300;
    @PropertyElement(name="DetailPageUrl")
    private String productDetailUrl;
    @PropertyElement(name="ProductPrice")
    private String productPrice;
    @PropertyElement(name="SalePrice")
    private String salePrice;
    @Element(name = "Benefit")
    private ProductBenefit benefit;

    //아래로는 안쓰는 거

    public ProductBenefit getBenefit() {
        return benefit;
    }

    public void setBenefit(ProductBenefit benefit) {
        this.benefit = benefit;
    }


    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetailUrl() {
        return productDetailUrl;
    }

    public void setProductDetailUrl(String productDetailUrl) {
        this.productDetailUrl = productDetailUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }


    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductImage300() {
        return productImage300;
    }

    public void setProductImage300(String productImage300) {
        this.productImage300 = productImage300;
    }
}

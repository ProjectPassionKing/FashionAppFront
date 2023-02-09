package com.example.fashionapp.Model.Entity.search;

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

    @PropertyElement(name="ProductImage")
    private String productImage;
    @PropertyElement(name="ProductImage100")
    private String productImage100;
    @PropertyElement(name="ProductImage110")
    private String productImage110;
    @PropertyElement(name="ProductImage120")
    private String productImage120;
    @PropertyElement(name="ProductImage130")
    private String productImage130;
    @PropertyElement(name="ProductImage140")
    private String productImage140;
    @PropertyElement(name="ProductImage150")
    private String productImage150;
    @PropertyElement(name="ProductImage170")
    private String productImage170;
    @PropertyElement(name="ProductImage200")
    private String productImage200;
    @PropertyElement(name="ProductImage250")
    private String productImage250;
    @PropertyElement(name="ProductImage270")
    private String productImage270;
    @PropertyElement(name = "Text1")
    private String text1;
    @PropertyElement(name = "Text2")
    private String text2;
    @PropertyElement(name = "SellerNick")
    private String sellerNick;
    @PropertyElement(name = "Seller")
    private String seller;
    @PropertyElement(name = "SellerGrd")
    private String sellerGrd;
    @PropertyElement(name = "Rating")
    private String rating;
    @PropertyElement(name = "Delivery")
    private String delivery;
    @PropertyElement(name = "ReviewCount")
    private String reviewcount;
    @PropertyElement(name = "BuySatisfy")
    private String buySatisfy;
    @PropertyElement(name = "MinorYn")
    private String minorYn;



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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
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

    public String getProductImage100() {
        return productImage100;
    }

    public void setProductImage100(String productImage100) {
        this.productImage100 = productImage100;
    }

    public String getProductImage120() {
        return productImage120;
    }

    public void setProductImage120(String productImage120) {
        this.productImage120 = productImage120;
    }

    public String getProductImage130() {
        return productImage130;
    }

    public void setProductImage130(String productImage130) {
        this.productImage130 = productImage130;
    }

    public String getProductImage140() {
        return productImage140;
    }

    public void setProductImage140(String productImage140) {
        this.productImage140 = productImage140;
    }

    public String getProductImage150() {
        return productImage150;
    }

    public void setProductImage150(String productImage150) {
        this.productImage150 = productImage150;
    }

    public String getProductImage170() {
        return productImage170;
    }

    public void setProductImage170(String productImage170) {
        this.productImage170 = productImage170;
    }

    public String getProductImage200() {
        return productImage200;
    }

    public void setProductImage200(String productImage200) {
        this.productImage200 = productImage200;
    }

    public String getProductImage250() {
        return productImage250;
    }

    public void setProductImage250(String productImage250) {
        this.productImage250 = productImage250;
    }

    public String getProductImage270() {
        return productImage270;
    }

    public void setProductImage270(String productImage270) {
        this.productImage270 = productImage270;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSellerGrd() {
        return sellerGrd;
    }

    public void setSellerGrd(String sellerGrd) {
        this.sellerGrd = sellerGrd;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(String reviewcount) {
        this.reviewcount = reviewcount;
    }

    public String getBuySatisfy() {
        return buySatisfy;
    }

    public void setBuySatisfy(String buySatisfy) {
        this.buySatisfy = buySatisfy;
    }

    public String getMinorYn() {
        return minorYn;
    }

    public void setMinorYn(String minorYn) {
        this.minorYn = minorYn;
    }

    public String getProductImage110() {
        return productImage110;
    }

    public void setProductImage110(String productImage110) {
        this.productImage110 = productImage110;
    }
}

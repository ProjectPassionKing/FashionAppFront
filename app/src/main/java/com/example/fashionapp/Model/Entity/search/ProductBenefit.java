package com.example.fashionapp.Model.Entity.search;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class ProductBenefit {
    @PropertyElement(name="Discount")
    private String discount;
    @PropertyElement(name = "Mileage")
    private String mileage;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}

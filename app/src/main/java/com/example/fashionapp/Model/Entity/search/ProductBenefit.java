package com.example.fashionapp.Model.Entity.search;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class ProductBenefit {
    @PropertyElement(name="Discount")
    private String discount;

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}

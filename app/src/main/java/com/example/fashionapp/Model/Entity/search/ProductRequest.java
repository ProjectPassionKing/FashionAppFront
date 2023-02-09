package com.example.fashionapp.Model.Entity.search;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml
public class ProductRequest {
    @Element(name = "Arguments")
    private ProductArguments arguments;
    @PropertyElement(name = "ProcessingTime")
    private String processingTime;

    public String getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(String processingTime) {
        this.processingTime = processingTime;
    }

    public ProductArguments getArguments() {
        return arguments;
    }

    public void setArguments(ProductArguments arguments) {
        this.arguments = arguments;
    }
}

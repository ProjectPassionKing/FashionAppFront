package com.example.fashionapp.Model.Entity.search;

import com.tickaroo.tikxml.annotation.Element;
import com.tickaroo.tikxml.annotation.Xml;

import java.util.List;

@Xml
public class ProductArguments {
    @Element(name = "Argument")
    private List<ProductArgument> argumentList;

    public List<ProductArgument> getArgumentList() {
        return argumentList;
    }

    public void setArgumentList(List<ProductArgument> argumentList) {
        this.argumentList = argumentList;
    }
}

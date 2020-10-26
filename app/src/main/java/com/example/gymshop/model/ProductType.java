package com.example.gymshop.model;

public class ProductType {
    private int id;
    private String typeName,typeImage;

    public ProductType(int id, String typeName, String typeImage) {
        this.id = id;
        this.typeName = typeName;
        this.typeImage = typeImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }
}

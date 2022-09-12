package com.neusoft.elm.po;

public class Food {

    private Integer foodID;
    private String foodName;
    private String foodExplain;
    private Double foodPrice;
    private Integer businessID;

    public Integer getFoodID() {
        return foodID;
    }

    public void setFoodID(Integer foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodExplain() {
        return foodExplain;
    }

    public void setFoodExplain(String foodExplain) {
        this.foodExplain = foodExplain;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Integer getBusinessId() {
        return businessID;
    }

    public void setBusinessId(Integer businessID) {
        this.businessID = businessID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Food f) {
            return this.getFoodID().equals(f.getFoodID());
        }
        return false;
    }

    @Override
    public String toString() {
        return "\n食品编号：" + this.foodID +
                "\n食品名称：" + this.foodName +
                "\n食品介绍：" + this.foodExplain +
                "\n食品价格：" + this.foodPrice +
                "\n所属商家：" + this.businessID;
    }

//get、set ... ...
}
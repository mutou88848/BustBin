package dust.clientBase.model;

import java.io.Serializable;

/**
 * Created by Pony on 2018/12/20.
 */

public class FoodModel implements Serializable {


    /**
     * foodTime : 2018-12-19 23:50
     * foodId : 2
     * foodCoordinates : 108.902027,34.249407
     * foodAddress : 西安市莲湖区
     * foodUserPhone : 15249243001
     * foodUserName : pony
     */

    private String foodTime;
    private int foodId;
    private String foodCoordinates;
    private String foodAddress;
    private String foodUserPhone;
    private String foodUserName;

    public String getFoodTime() {
        return foodTime;
    }

    public void setFoodTime(String foodTime) {
        this.foodTime = foodTime;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodCoordinates() {
        return foodCoordinates;
    }

    public void setFoodCoordinates(String foodCoordinates) {
        this.foodCoordinates = foodCoordinates;
    }

    public String getFoodAddress() {
        return foodAddress;
    }

    public void setFoodAddress(String foodAddress) {
        this.foodAddress = foodAddress;
    }

    public String getFoodUserPhone() {
        return foodUserPhone;
    }

    public void setFoodUserPhone(String foodUserPhone) {
        this.foodUserPhone = foodUserPhone;
    }

    public String getFoodUserName() {
        return foodUserName;
    }

    public void setFoodUserName(String foodUserName) {
        this.foodUserName = foodUserName;
    }
}

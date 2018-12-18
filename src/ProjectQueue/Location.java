/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class Location {

    private String nameLocation;
    private int price;

    public Location() {

    }

    public Location(String nameLocation, int price) {
        this.nameLocation = nameLocation;
        this.price = price;

    }

    public String getNameLocation() {
        return nameLocation;
    }

    public int getPrice() {
        return price;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Location{" + "nameLocation=" + nameLocation + ", price=" + price + '}';
    }

}

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
public class Queue {

    private Drivers driver;
    private String firstTime;
    private String driverNumber;

    public Queue() {

    }

    public Queue(String firstTime, String driverNumber) {

        this.firstTime = firstTime;
        this.driverNumber = driverNumber;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    @Override
    public String toString() {
        return "Queue{" + "driver=" + driver + ", firstTime=" + firstTime + ", driverNumber=" + driverNumber + '}';
    }

}

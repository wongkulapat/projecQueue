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
public class Passenger {

    private String passengerID;
    private String passengerFN;
    private String passengerLN;
    private String passengerTel;
    private String passengerCount;

    public Passenger() {

    }

    public Passenger(String passengerID, String passengerFN, String passengerLN, String passengerTel, String passengerCount) {
        this.passengerID = passengerID;
        this.passengerFN = passengerFN;
        this.passengerLN = passengerLN;
        this.passengerTel = passengerTel;
        this.passengerCount = passengerCount;
    }

    public String getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(String passengerID) {
        this.passengerID = passengerID;
    }

    public String getPassengerFN() {
        return passengerFN;
    }

    public void setPassengerFN(String passengerFN) {
        this.passengerFN = passengerFN;
    }

    public String getPassengerLN() {
        return passengerLN;
    }

    public void setPassengerLN(String passengerLN) {
        this.passengerLN = passengerLN;
    }

    public String getPassengerTel() {
        return passengerTel;
    }

    public void setPassengerTel(String passengerTel) {
        this.passengerTel = passengerTel;
    }

    public String getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(String passengerCount) {
        this.passengerCount = passengerCount;
    }

    @Override
    public String toString() {
        return "Passenger{" + "passengerID=" + passengerID + ", passengerFN=" + passengerFN + ", passengerLN=" + passengerLN + ", passengerTel=" + passengerTel + ", passengerCount=" + passengerCount + '}';
    }

}

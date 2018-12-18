/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import java.util.List;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class ServiceQueue {

    DriverDao driverDao;
    PassengerDao passengerDao;
    QueueDao queueDao;
    LocationDao locationDao;

    public ServiceQueue() {
        driverDao = new DriverDao();
        queueDao = new QueueDao();
        passengerDao = new PassengerDao();
        locationDao = new LocationDao();
    }

    public boolean addQueue(Queue queue) {
        return queueDao.insert(queue);
    }

    public List<Drivers> findAllDriver() {
        return driverDao.findAll();
    }

    public List<Drivers> findOneDriver(String driverNumber) {
        return driverDao.findOneDriver(driverNumber);
    }

    public Drivers findDriverByNumber(String driverNumber) {
        return driverDao.findByNumber(driverNumber);
    }

    public List<Queue> findAllQueue() {
        return queueDao.findAll();
    }

    public List<Passenger> findAllPassenger() {
        return passengerDao.findAll();
    }

    public Passenger findPassengerByID(String passengerID) {
        return passengerDao.findByID(passengerID);
    }

    public List<Location> findAllLocation() {
        return locationDao.findAll();
    }

    public Location findLocationByName(String nameLocation) {
        return locationDao.findByName(nameLocation);
    }

    public boolean deleteQueue(String driverNumber) {
        return queueDao.delete(driverNumber);
    }

    public boolean updateIncomeAndCountDriver(String driverNumber, String driverIncome, String driverCount) {
        return driverDao.updateIncomeAndCount(driverNumber, driverIncome, driverCount);
    }

    public List<Passenger> findOnePassenger(String passengerID) {
        return passengerDao.findOnePasssenger(passengerID);
    }

    public boolean updateCountPassenger(String passengerID, String passengerCount) {
        return passengerDao.updateCount(passengerID, passengerCount);
    }

}

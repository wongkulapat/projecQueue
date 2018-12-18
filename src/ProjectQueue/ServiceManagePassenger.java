package ProjectQueue;

import java.util.List;

public class ServiceManagePassenger {

    PassengerDao PassengerDao;

    public ServiceManagePassenger() {
        PassengerDao = new PassengerDao();

    }

    public boolean addPassenger(Passenger passenger) {
        return PassengerDao.insert(passenger);
    }

    public List<Passenger> findAllPassenger() {
        return PassengerDao.findAll();
    }

    public Passenger findPassengerByID(String PassengerID) {
        return PassengerDao.findByID(PassengerID);
    }

    public boolean deletePassenger(String passengerID) {
        return PassengerDao.delete(passengerID);
    }

    public boolean updatePassengerByID(String passengerID, Passenger newPassenger) {
        return PassengerDao.update(passengerID, newPassenger);
    }

}

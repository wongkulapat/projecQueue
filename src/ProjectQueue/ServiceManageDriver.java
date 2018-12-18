package ProjectQueue;

import java.util.List;
import ProjectQueue.DriverDao;
import ProjectQueue.Drivers;

public class ServiceManageDriver {

    DriverDao DriverDao;

    public ServiceManageDriver() {
        DriverDao = new DriverDao();

    }

    public boolean addDriver(Drivers driver) {
        return DriverDao.insert(driver);
    }

    public List<Drivers> findAllDriver() {
        return DriverDao.findAll();
    }

    public Drivers findDriverByNumber(String driverNumber) {
        return DriverDao.findByNumber(driverNumber);
    }

    public boolean deleteDriver(String driverNumber) {
        return DriverDao.delete(driverNumber);
    }

    public boolean updateDriver(String driverNumber, Drivers newDriver) {
        return DriverDao.update(driverNumber, newDriver);
    }

}

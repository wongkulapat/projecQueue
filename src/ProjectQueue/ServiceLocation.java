/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import java.util.List;
import javax.swing.JTextField;

public class ServiceLocation {

    LocationDao LocationDao;

    public ServiceLocation() {
        LocationDao = new LocationDao();

    }

    public boolean addLocation(Location location) {
        return LocationDao.insert(location);
    }

    public List<Location> findAllLocation() {
        return LocationDao.findAll();
    }

    public Location findLocationByName(String nameLocation) {
        return LocationDao.findByName(nameLocation);
    }

    public boolean deleteLocation(String nameLocation) {
        return LocationDao.delete(nameLocation);
    }

    public boolean updateLocation(String price, Location newLocation) {
        return LocationDao.update(price, newLocation);
    }

}

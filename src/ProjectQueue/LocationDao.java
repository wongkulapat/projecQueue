/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class LocationDao {

    private static MongoCollection<Location> loCol;

    public LocationDao() {
        loCol = Database.getDatabase().getCollection("location", Location.class);
    }

    public Location findByName(String nameLocation) {
        return loCol.find(eq("nameLocation", nameLocation)).first();
    }

    public List<Location> findAll() {
        return loCol.find().into(new ArrayList<>());
    }

    public boolean insert(Location location) {
        try {
            loCol.insertOne(location);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String nameLocation) {
        Document LoDoc = new Document("nameLocation", nameLocation);
        try {
            loCol.deleteOne(LoDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(String nameLocation, Location newLocation) {
        Gson gson = new Gson();
        Document newPas = Document.parse(gson.toJson(newLocation));
        Document LoDoc = new Document("$set", newPas);
        try {
            loCol.updateOne(eq("nameLocation", nameLocation), LoDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

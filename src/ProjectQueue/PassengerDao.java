/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.google.gson.Gson;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class PassengerDao {

    private static MongoCollection<Passenger> pasCol;

    public PassengerDao() {
        pasCol = Database.getDatabase().getCollection("passenger", Passenger.class);
    }

    public Passenger findByID(String passengerID) {
        return pasCol.find(eq("passengerID", passengerID)).first();
    }

    public List<Passenger> findOnePasssenger(String passengerID) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("passengerID", passengerID);
        return pasCol.find(whereQuery).into(new ArrayList<>());
    }

    public List<Passenger> findAll() {
        return pasCol.find().into(new ArrayList<>());
    }

    public boolean insert(Passenger passenger) {
        try {
            pasCol.insertOne(passenger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCount(String passengerID, String count) {
        Document query = new Document();
        query.append("passengerID", passengerID);
        Document setData = new Document();
        setData.append("passengerCount", count);
        Document update = new Document();
        update.append("$set", setData);
        try {
            pasCol.updateOne(query, update);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(String passengerID, Passenger newPassenger) {
        Gson gson = new Gson();
        Document newPas = Document.parse(gson.toJson(newPassenger));
        Document passengerDoc = new Document("$set", newPas);
        try {
            pasCol.updateOne(eq("passengerID", passengerID), passengerDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String passengerID) {
        Document passengerDoc = new Document("passengerID", passengerID);
        try {
            pasCol.deleteOne(passengerDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

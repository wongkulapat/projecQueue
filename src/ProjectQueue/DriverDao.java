/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import static org.bson.Document.parse;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class DriverDao {

    private static MongoCollection<Drivers> driCol;

    public DriverDao() {
        driCol = Database.getDatabase().getCollection("driver", Drivers.class);

    }

    public Drivers findByNumber(String driverNumber) {
        return driCol.find(eq("driverNumber", driverNumber)).first();
    }

    public List<Drivers> findAll() {
        return driCol.find().into(new ArrayList<>());
    }

    public List<Drivers> findOneDriver(String driverNumber) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("driverNumber", driverNumber);
        return driCol.find(whereQuery).into(new ArrayList<>());
    }

    public boolean insert(Drivers driver) {
        try {
            driCol.insertOne(driver);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateIncomeAndCount(String driNumber, String driverIncome, String driverCount) {
        Document query = new Document();
        query.append("driverNumber", driNumber);
        Document setData = new Document();
        setData.append("driverIncome", driverIncome).append("driverCount", driverCount);
        Document update = new Document();
        update.append("$set", setData);
        try {
            driCol.updateOne(query, update);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
   

    public boolean delete(String driverNumber) {
        Document driverDoc = new Document("driverNumber", driverNumber);
        try {
            driCol.deleteOne(driverDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(String DriverNumber, Drivers newDriver) {
        Gson gson = new Gson();
        Document newDri = Document.parse(gson.toJson(newDriver));
        Document driverDoc = new Document("$set", newDri);
        try {
            driCol.updateOne(eq("driverNumber", DriverNumber), driverDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    }

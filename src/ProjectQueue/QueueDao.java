/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.bson.Document;
import static org.bson.Document.parse;
import org.bson.conversions.Bson;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class QueueDao {

    private static MongoCollection<Queue> queueCol;

    public QueueDao() {
        queueCol = Database.getDatabase().getCollection("queues", Queue.class);
    }

    public boolean insert(Queue queue) {
        try {
            queueCol.insertOne(queue);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Queue> findAll() {
        return queueCol.find().into(new ArrayList<>());
    }

    public boolean delete(String driNumber) {
        Document queueDoc = new Document("driverNumber", driNumber);
        try {
            queueCol.deleteOne(queueDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        Database.Database();
        QueueDao dao = new QueueDao();
        Queue ada = new Queue("14:00:02", "1");
        dao.insert(ada);
        System.out.println(dao.findAll().toString());
    }

}

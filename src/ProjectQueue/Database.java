/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjectQueue;



import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

/**
 *
 * @author Sunisa Liangtrakool
 */
public class Database {
    private static MongoClientURI uri;
    private static MongoClient client;
    private static MongoDatabase db;
    
   

    public static void Database(){
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        
        uri  = new MongoClientURI("mongodb://test:test1122@ds063140.mlab.com:63140/projectqueue");
        client = new MongoClient(uri);
        db = client.getDatabase(uri.getDatabase());
        db = db.withCodecRegistry(pojoCodecRegistry);

    }
    
    public static MongoDatabase getDatabase(){
        return db;
    }
}
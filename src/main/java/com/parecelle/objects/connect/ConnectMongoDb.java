package com.parecelle.objects.connect;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class ConnectMongoDb {
    public static MongoClient getConnection() {
        Logger.getLogger( "org.mongodb.driver" ).setLevel(Level.WARNING); 
        String connectionString = "mongodb+srv://ihaingoram:root@cluster0.dbhwwvp.mongodb.net/?retryWrites=true&w=majority";
        // mongodb+srv://mongodb:root@cluster0.febdmep.mongodb.net/?retryWrites=true&w=majority
        // mongodb+srv://ihaingoram:root@cluster0.dbhwwvp.mongodb.net/?retryWrites=true&w=majority
        ConnectionString connString = new ConnectionString(connectionString);

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
            .codecRegistry(pojoCodecRegistry)
            .applyConnectionString(connString).build();
            
        MongoClient mongoClient = MongoClients.create(settings); 
        // MongoClient mongoClient = MongoClients.create(connString);     
                
        return mongoClient;
    } 

}

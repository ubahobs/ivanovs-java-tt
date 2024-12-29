package com.tdl.catfact.util;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MongoUtil {
    private static final String databaseName = "testDB";
    private static final String collectionName = "testCollection";
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static void connectToDB () {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase(databaseName);
    }

    public static void insertTestData () {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            Document doc = new Document("wordCount", random.nextInt(30) + 1)
                    .append("charCount", random.nextInt(181) + 20);
            collection.insertOne(doc);
        }
    }

    public static List<Document> getTestData () {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        return collection.find().into(new ArrayList<>());
    }

    public static void cleanUp () {
        database.getCollection(collectionName).drop();
        mongoClient.close();
    }
}

package com.manahilsher.WebCrawler;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;

import io.github.cdimascio.dotenv.Dotenv;

public class UrlRepository {

    MongoCollection<Document> urlsCollection;

    public UrlRepository() {
        Dotenv dotenv = null;
        dotenv = Dotenv.configure().load();

        String MONGODB_USER = dotenv.get("MONGODB_USER");
        String MONGODB_PASSWORD = dotenv.get("MONGODB_PASSWORD");
        String DATABASE_NAME = dotenv.get("DATABASE_NAME");

        MongoClient client = MongoClients.create(
                "mongodb+srv://" + MONGODB_USER + ":" + MONGODB_PASSWORD
                        + "@cluster0.agqlr.mongodb.net/?retryWrites=true&w=majority");

        MongoDatabase database = client.getDatabase(DATABASE_NAME);
        this.urlsCollection = database.getCollection("urls");
    }

    public FindIterable<Document> findAll() {
        return urlsCollection.find();
    }

    public ObjectId insert(String url) {
        Document doc = new Document("url", url);
        ObjectId id = urlsCollection.insertOne(doc).getInsertedId().asObjectId().getValue();
        return id;
    }

    public Document find(String url) {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("url", url);
        return urlsCollection.find(whereQuery).first();
    }

}

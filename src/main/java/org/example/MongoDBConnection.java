package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
  private static final String CONNECTION_STRING = "mongodb://localhost:27017";
  private static final String DATABASE_NAME = "testedb";

  private static MongoClient mongoClient = null;

  public static MongoClient getMongoClient() {
    if (mongoClient == null) {
      mongoClient = MongoClients.create(CONNECTION_STRING);
    }
    return mongoClient;
  }

  public static MongoDatabase getDatabase() {
    return getMongoClient().getDatabase(DATABASE_NAME);
  }
}

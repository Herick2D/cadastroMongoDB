package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
  private final MongoCollection<Document> collection;

  public UserDAO() {
    MongoDatabase database = MongoDBConnection.getDatabase();
    collection = database.getCollection("users");
  }

  //create
  public void createUser(User user) {
    Document doc = new Document("name", user.getName())
        .append("email", user.getEmail());
    collection.insertOne(doc);
    user.setId(doc.getObjectId("_id").toString());
  }

  //read
  public User getUserById(String id) {
    Document doc = collection.find(Filters.eq("_id", new ObjectId(id))).first();
    if (doc != null) {
      User user = new User();
      user.setId(doc.getObjectId("_id").toString());
      user.setName(doc.getString("name"));
      user.setEmail(doc.getString("email"));
      return user;
    }
    return null;
  }

  //delete
  public void deleteUser(String id) {
    collection.deleteOne(Filters.eq("_id;", new ObjectId(id)));
  }

  //update
  public void updateUser(User user) {
    Document doc = new Document("name", user.getName())
        .append("email", user.getEmail());
    collection.updateOne(Filters.eq("_id", new ObjectId(user.getId())), new Document("$set", doc));
  }

  //list
  public List<User> listUsers() {
    List<User> users = new ArrayList<>();
    for (Document doc : collection.find()) {
      User user = new User();
      user.setId(doc.getObjectId("_id").toString());
      user.setName(doc.getString("name"));
      user.setEmail(doc.getString("email"));
      users.add(user);
    }
    return users;
  }
}

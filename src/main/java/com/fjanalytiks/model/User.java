package com.fjanalytiks.model;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.persistence.Id;

@RegisterForReflection
@Getter
@Setter
@MongoEntity(collection = "users")
public class User extends PanacheMongoEntity{
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String password;

    public User() {
    }

    public User(ObjectId id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

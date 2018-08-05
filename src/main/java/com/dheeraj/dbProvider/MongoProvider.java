package com.dheeraj.dbProvider;

import com.mongodb.MongoClient;
import com.typesafe.config.Config;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.inject.Singleton;

/**
 * Created by IntelliJ IDEA.
 * User: Dheeraj Reddy
 * Date: 04/08/18
 */

@Singleton
public class MongoProvider {
    private String url = "mongodb.uri";
    private Config configuration;
    private Datastore datastore;
    private String context;

    public MongoProvider(Config configuration, String context) {
        this.configuration = configuration;
        this.context = context;
    }

    public void createDataStore() {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("models");
        datastore = morphia.createDatastore(new MongoClient(configuration.getString(url)), context);
        datastore.ensureIndexes();
    }

    public Datastore getDatastore() {
        if (datastore == null) {
            createDataStore();
        }
        return datastore;
    }
}

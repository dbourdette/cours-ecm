package fr.cmm;

import java.net.UnknownHostException;

import javax.inject.Inject;

import com.mongodb.gridfs.GridFS;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import static fr.cmm.SpringProfiles.INTEG;

@Configuration
@ComponentScan("fr.cmm.service")
public class SpringConfig {
    @Inject
    private Environment env;

    @Bean
    public String getDbName() {
        if (env.acceptsProfiles(INTEG)) {
            return "cmm-integ";
        } else {
            return "cmm";
        }
    }

    @Bean
    public DB db() throws UnknownHostException {
        return new MongoClient().getDB(getDbName());
    }

    @Bean
    public GridFS gridFS() throws UnknownHostException {
        return new GridFS(db());
    }

    @Bean
    public Jongo jongo() throws UnknownHostException {
        return new Jongo(db());
    }

    @Bean
    public MongoCollection recipeCollection() throws UnknownHostException {
        MongoCollection collection = jongo().getCollection("recipes");

        collection.ensureIndex("{randomLocation: '2d'}");

        return collection;
    }
}

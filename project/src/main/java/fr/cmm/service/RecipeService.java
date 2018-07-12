package fr.cmm.service;

import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.springframework.stereotype.Service;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;

@Service
public class RecipeService {
    @Inject
    private MongoCollection recipeCollection;

    public Iterable<Recipe> findByQuery(PageQuery query) {
        String mongoQuery = "{}";
        String[] params = {};

        if (query.getTag() != null && !"".equals(query.getTag())) {
            mongoQuery = "{tags: #}";
            params = new String[] {query.getTag()};
        }

        return recipeCollection
                .find(mongoQuery, (Object[]) params)
                .skip(query.skip())
                .limit(query.getSize())
                .as(Recipe.class);
    }

    public long countByQuery(PageQuery query) {
        return recipeCollection.count();
    }

    public Iterator<Recipe> findRandom(int count) {
        return recipeCollection.find("{randomLocation: {$near: [#, 0]}}", Math.random()).limit(count).as(Recipe.class);
    }

    public Recipe findById(String id) {
        return recipeCollection.findOne(new ObjectId(id)).as(Recipe.class);
    }

    public void save(Recipe recipe) {
        recipeCollection.save(recipe);
    }

    public List<String> findAllTags() {
        return recipeCollection.distinct("tags").as(String.class);
    }
}

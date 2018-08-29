package fr.cmm.boot;


import fr.cmm.domain.Recipe;
import fr.cmm.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.jongo.MongoCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static fr.cmm.SpringProfiles.TEST;
import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FillDatabaseWithRandomTaskConfig.class)
@ActiveProfiles(TEST)
public class FillDatabaseWithRandomTask {
    @Inject
    private MongoCollection recipeCollection;

    @Inject
    private ImageService imageService;

    static int count = 1;

    static final String[] TAGS = {"choucroute", "alsace", "choux", "champignon"};

    @Test
    public void fillDbWithRandomRecipes() throws IOException {
        recipeCollection.remove();

        for (int i = 0; i < 100; i++) {
            recipeCollection.insert(randomRecipe());
        }
    }

    private Recipe randomRecipe() throws IOException {
        Recipe recipe = new Recipe();

        recipe.setTitle("La choucroute aux champignons " + count++);
        recipe.setDate(new Date());
        recipe.setIntro("De la choucroute, des champignons et un peu d'espiéglerie");
        recipe.setText("du texte\ndu texte\ndu texte\net encore du texte.");
        recipe.setImageId(randomImage());
        recipe.setTags(randomTags());

        return recipe;
    }

    private String randomImage() throws IOException {
        byte[] data = IOUtils.toByteArray(getClass().getResourceAsStream("/pic" + random(3) + ".jpg"));

        return imageService.save(data, "image/jpeg");
    }

    private List<String> randomTags() throws IOException {
        return asList(TAGS[random(TAGS.length) - 1], TAGS[random(TAGS.length) - 1]);
    }

    private int random(int max) {
        return new Random().nextInt(max) + 1;
    }
}

package fr.cmm.boot;

import javax.inject.Inject;

import org.jongo.MongoCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static fr.cmm.SpringProfiles.TEST;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = FillDatabaseWithRandomTaskConfig.class)
@ActiveProfiles(TEST)
public class ResetDatabaseTask {
    @Inject
    private MongoCollection recipeCollection;

    @Test
    public void fillDbWithRandomRecipes() {
        recipeCollection.remove();
    }
}

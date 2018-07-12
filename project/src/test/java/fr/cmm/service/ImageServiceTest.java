package fr.cmm.service;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import java.io.IOException;

import static fr.cmm.SpringProfiles.INTEG;
import static fr.cmm.SpringProfiles.TEST;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ImageServiceTestConfig.class)
@ActiveProfiles(INTEG)
public class ImageServiceTest {
    @Inject
    private ImageService imageService;

    @Inject
    private GridFS gridFS;

    @Before
    @After
    public void clean() {
        gridFS.remove(new BasicDBObject());
    }

    @Test
    public void save() throws IOException {
        String id = imageService.save(new byte[]{'a', 'b', 'c'}, "fake/mime");

        assertNotNull(id);
        assertEquals("fake/mime", gridFS.findOne(new ObjectId(id)).getContentType());

        byte[] data = new byte[3];
        gridFS.findOne(new ObjectId(id)).getInputStream().read(data);

        assertArrayEquals(new byte[]{'a', 'b', 'c'}, data);
    }

    @Test
    public void findById() throws IOException {
        String id = imageService.save(new byte[]{'a', 'b', 'c'}, "fake/mime");

        ImageInfo imageInfo = imageService.findById(id);

        assertEquals("fake/mime", imageInfo.getContentType());
        assertArrayEquals(new byte[]{'a', 'b', 'c'}, imageInfo.getData());
    }

    @Test
    public void findByIdMissingImage() throws IOException {
        ImageInfo imageInfo = imageService.findById(new ObjectId().toString());

        assertNull(imageInfo);
    }
}
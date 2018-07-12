package fr.cmm.service;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;

@Service
public class ImageService {
    @Inject
    private GridFS gridFS;

    public String save(byte[] data, String contentType) {
        GridFSInputFile file = gridFS.createFile(data);

        file.setContentType(contentType);
        file.save();

        return file.getId().toString();
    }

    public ImageInfo findById(String id) throws IOException {
        GridFSDBFile file = gridFS.findOne(new ObjectId(id));

        if (file == null) {
            return null;
        }

        ImageInfo imageInfo = new ImageInfo();
        imageInfo.setContentType(file.getContentType());
        imageInfo.setData(IOUtils.toByteArray(file.getInputStream()));

        return imageInfo;
    }
}

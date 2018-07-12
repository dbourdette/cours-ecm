package fr.cmm.controller;

import fr.cmm.service.ImageInfo;
import fr.cmm.service.ImageService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ImageController {
    @Inject
    private ImageService imageService;

    @RequestMapping("/image/{id}")
    public void image(@PathVariable("id") String id, HttpServletResponse response) throws IOException {
        ImageInfo imageInfo = imageService.findById(id);

        if (imageInfo == null) {
            response.sendError(404);

            return;
        }

        response.setContentType(imageInfo.getContentType());

        IOUtils.write(imageInfo.getData(), response.getOutputStream());
    }
}

package com.maruszka.gptai.component;

import com.maruszka.gptai.exception.ImageConversionException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Component
public class ImageConverter {

    @Value("${gpt.output-directory}")
    private String path;

    @Value("${gpt.extension}")
    private String extension;

    public void base64ToImage(String encodedString) {
        String randomName = RandomStringUtils.randomAlphabetic(10);
        String filePath = path + randomName + extension;

        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), decodedBytes);
        } catch (IOException e) {
            throw new ImageConversionException(e.getMessage());
        }
    }
}

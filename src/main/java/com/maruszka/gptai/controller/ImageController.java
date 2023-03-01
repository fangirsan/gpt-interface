package com.maruszka.gptai.controller;

import com.maruszka.gptai.model.image.ImagePayload;
import com.maruszka.gptai.model.image.ImageResponseFormat;
import com.maruszka.gptai.model.image.Size;
import com.maruszka.gptai.service.ImageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/generate/{prompt}/{number}/{size}/{imageResponseFormat}")
    public void generateImage(
            @PathVariable String prompt,
            @PathVariable int number,
            @PathVariable Size size,
            @PathVariable ImageResponseFormat imageResponseFormat) {

        ImagePayload payload = ImagePayload.builder()
                .prompt(prompt)
                .n(number)
                .size(size.getDescription())
                .imageResponseFormat(imageResponseFormat)
                .build();

        imageService.createImage(payload);
    }
}

package com.maruszka.gptai.service;

import com.maruszka.gptai.component.ImageConverter;
import com.maruszka.gptai.model.image.Content;
import com.maruszka.gptai.model.image.ImagePayload;
import com.maruszka.gptai.model.image.ImageResponse;
import com.maruszka.gptai.model.image.ImageResponseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${gpt.token}")
    private String privateKey;

    private final RestTemplate restTemplate;
    private final ImageConverter imageConverter;

    private static final String AUTHORIZATION = "Authorization";

    private static final String IMAGE_ENDPOINT = "https://api.openai.com/v1/images/generations";

    public void createImage(ImagePayload payload) {
        String message = String.format("Generating image from the following prompt: %s", payload.getPrompt());
        log.info(message);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add(AUTHORIZATION, "Bearer " + privateKey);

        ImageResponseFormat format = payload.getImageResponseFormat();
        HttpEntity<ImagePayload> entity = new HttpEntity<>(payload, headers);

        ResponseEntity<ImageResponse> responseEntity = restTemplate.postForEntity(
                IMAGE_ENDPOINT,
                entity,
                ImageResponse.class
        );

        HttpStatusCode statusCode = responseEntity.getStatusCode();
        if (statusCode.equals(HttpStatus.OK)) {
            ImageResponse response = responseEntity.getBody();
            assert response != null;

            List<Content> data = response.data();
            if (format == ImageResponseFormat.url) {
                data.forEach(url -> log.info(url.getUrl()));
            } else {
                data.forEach(url -> imageConverter.base64ToImage(url.getB64_json()));
                log.info("Generation completed");
            }

        } else {
            log.warn("Unable to fetch response, status code was: " + statusCode);
        }
    }
}

package com.maruszka.gptai.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ImagePayload {

    String prompt;
    int n;
    String size;

    @JsonProperty("response_format")
    ImageResponseFormat imageResponseFormat;
}


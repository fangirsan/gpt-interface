package com.maruszka.gptai.model.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Content {

    String url;
    String b64_json;
}

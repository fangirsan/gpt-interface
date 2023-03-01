package com.maruszka.gptai.model.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size {

    small("256x256"),
    medium("512x512"),
    large("1024x1024");

    private String description;
}

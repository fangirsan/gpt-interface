package com.maruszka.gptai.model.image;

import java.time.LocalDate;
import java.util.List;

public record ImageResponse(LocalDate created, List<Content> data) {}

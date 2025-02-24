package com.cyy.chat.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "模型参数")
public class ModelConfigDto {

    @Schema(description = "模型的温度", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String temperature;

    @Schema(description = "模型每次输出的最大 token 数", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String maxTokens;
}

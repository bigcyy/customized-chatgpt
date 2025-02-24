package com.cyy.chat.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "模型参数")
@Getter
@Setter
@ToString
public class ModelDto {
    @Schema(description = "模型 id，数据库自增")
    private Long id;

    @Schema(description = "模型在系统中的显示名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String displayName;

    @Schema(description = "模型在提供商中的名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String modelName;

    @Schema(description = "模型的类型，枚举值", requiredMode = Schema.RequiredMode.REQUIRED)
    private String modelType;

    @Schema(description = "模型的提供商", requiredMode = Schema.RequiredMode.REQUIRED)
    private String provider;

    @Schema(description = "模型的请求地址", requiredMode = Schema.RequiredMode.AUTO)
    private String apiUrl;

    @Schema(description = "请求模型需要的 apiKey", requiredMode = Schema.RequiredMode.AUTO)
    private String apiKey;

    @Schema(description = "模型配置,json类型", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private ModelConfigDto modelConfig;

}

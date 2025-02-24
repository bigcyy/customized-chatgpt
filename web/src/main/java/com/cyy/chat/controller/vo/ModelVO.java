package com.cyy.chat.controller.vo;

import com.cyy.common.enums.ModelType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "模型 VO，用于展示模型")
@Getter
@Setter
@ToString
public class ModelVO {
    @Schema(description = "模型 id")
    private Long id;

    @Schema(description = "模型在系统中的显示名称")
    private String displayName;

    @Schema(description = "模型在提供商中的名称")
    private String modelName;

    @Schema(description = "模型的类型，枚举值")
    private ModelType modelType;

    @Schema(description = "模型的提供商")
    private ProviderVO provider;

}

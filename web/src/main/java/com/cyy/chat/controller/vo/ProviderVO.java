package com.cyy.chat.controller.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "供应商 VO")
@Getter
@Setter
@ToString
public class ProviderVO {
    @Schema(description = "供应商名称")
    private String providerName;
    @Schema(description = "供应商图标 svg")
    private String providerIconPath;
}

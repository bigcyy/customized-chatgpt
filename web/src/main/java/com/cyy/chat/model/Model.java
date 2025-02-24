package com.cyy.chat.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 
 * </p>
 *
 * @author CYY
 * @since 2025-02-20
 */
@Getter
@Setter
@ToString
@Schema(description = "模型")
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "模型 id，数据库自增")
    private Long id;

    @Schema(description = "模型在系统中的显示名称")
    private String displayName;

    @Schema(description = "模型在提供商中的名称")
    private String modelName;

    @Schema(description = "模型的类型，枚举值")
    private String modelType;

    @Schema(description = "模型的提供商，枚举值")
    private String provider;

    @Schema(description = "模型的请求地址")
    private String apiUrl;

    @Schema(description = "请求模型需要的 apiKey")
    private String apiKey;

    @Schema(description = "模型配置,json类型",defaultValue = "{\"temperature\": 0.70, \"maxTokens\": 1024}")
    private String modelConfig;
}

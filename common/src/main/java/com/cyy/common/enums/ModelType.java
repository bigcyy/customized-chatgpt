package com.cyy.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ModelType {
    LLM("LLM","大语言模型","在应用中与AI对话的推理模型。"),
    EMBEDDING("EMBEDDING","向量模型","在知识库中对文档内容进行向量化的模型。");

    /**
     * key 应该和枚举名相同，这样在反序列化时才能自动反序列到对应的 Enum
     */
    private String key;
    private String name;
    private String desc;

    ModelType(String key, String name, String desc) {
        this.key = key;
        this.name = name;
        this.desc = desc;
    }
}

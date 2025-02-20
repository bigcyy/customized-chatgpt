package com.cyy.chat.provider.adaptor;

import org.springframework.ai.model.ChatModelDescription;
import org.springframework.ai.model.EmbeddingModelDescription;
import org.springframework.ai.model.ModelDescription;
import com.cyy.common.enums.ModelType;

public class ModelTypeAdaptor {
    public static ModelType resolveType(ModelDescription model) {
        if (model instanceof ChatModelDescription) {
            return ModelType.LLM;
        } else if (model instanceof EmbeddingModelDescription) {
            return ModelType.EMBEDDING;
        }
        throw new IllegalArgumentException("未知的模型类型: " + model.getClass());
    }
}

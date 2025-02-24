package com.cyy.chat.provider;


import com.cyy.common.enums.ModelType;
import org.springframework.ai.model.ModelDescription;

import java.util.List;

public interface ModelProvider {
    List<? extends ModelDescription> listSupportedModels();
    List<? extends ModelDescription> listSupportedModelByTypes(List<ModelType> type);
    String getProviderName();
    String getProviderIconPath();
    List<ModelType> listSupportedModelTypes();
    Boolean checkModelConnect(String baseUrl, String apiKey, String modelId);
}

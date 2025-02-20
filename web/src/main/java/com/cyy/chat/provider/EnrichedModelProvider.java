package com.cyy.chat.provider;

import com.cyy.chat.provider.adaptor.ModelTypeAdaptor;
import com.cyy.common.enums.ModelType;
import org.springframework.ai.model.ModelDescription;

import java.util.List;

public interface EnrichedModelProvider extends ModelProvider{
    /**
     * 模板方法，根据可提供的模型，获取模型提供者可以提供的模型类型
     */
    default List<ModelType> listSupportedModelTypes() {
        return listSupportedModels()
                .stream()
                .map(ModelTypeAdaptor::resolveType)
                .distinct()
                .toList();
    }

    @Override
    default List<? extends ModelDescription> listSupportedModelByTypes(List<ModelType> type) {
        return listSupportedModels().stream().filter(item->{
            ModelType modelType = ModelTypeAdaptor.resolveType(item);
            return type.contains(modelType);
        }).toList();
    }
}

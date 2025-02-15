package com.cyy.chat.provider;

import com.cyy.common.exception.SystemGlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ModelFactory {
    private Map<String, ModelProvider> providerRegistry = new ConcurrentHashMap<>();

    @Autowired
    public ModelFactory(List<ModelProvider> providers) {
        providers.forEach(provider -> providerRegistry.put(provider.getProviderName(), provider));
    }

    public ModelProvider getProvider(String providerName) {
        ModelProvider modelProvider = providerRegistry.get(providerName);
        if(modelProvider == null){
            throw new SystemGlobalException("Unsupported Provider " + providerName);
        }

        return modelProvider;
    }

    public Collection<ModelProvider> listProviders(){
        return providerRegistry.values();
    }
}

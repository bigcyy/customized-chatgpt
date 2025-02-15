package com.cyy.chat.provider;

import org.springframework.ai.anthropic.api.AnthropicApi;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AnthropicProvider implements ModelProvider{
    @Override
    public List<String> listSupportedModels() {
        return Arrays
                .stream(AnthropicApi.ChatModel.values())
                .map(AnthropicApi.ChatModel::name)
                .toList();
    }

    @Override
    public String getProviderName() {
        return "Anthropic";
    }

    @Override
    public String getProviderIconPath() {
        return "";
    }
}

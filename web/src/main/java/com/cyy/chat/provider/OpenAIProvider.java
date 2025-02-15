package com.cyy.chat.provider;

import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class OpenAIProvider implements ModelProvider {
    @Override
    public List<String> listSupportedModels() {
        return Arrays
                .stream(OpenAiApi.ChatModel.values())
                .map(OpenAiApi.ChatModel::getName)
                .toList();
    }

    @Override
    public String getProviderName() {
        return "OpenAI";
    }

    @Override
    public String getProviderIconPath() {
        return "";
    }
}

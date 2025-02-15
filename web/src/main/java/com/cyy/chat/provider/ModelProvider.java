package com.cyy.chat.provider;

import java.util.List;

public interface ModelProvider {
    List<String> listSupportedModels();
    String getProviderName();
    String getProviderIconPath();
}

package com.cyy.chat.model.result;

import com.google.api.Usage;

import java.util.List;

/**
 * @author CYY
 * @date 2023年03月10日 下午10:54
 * @description
 */
public class EmbeddingsApiResult {
    private String object;
    private List<EmbeddingObj> data;
    private String model;
    private Usage usage;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<EmbeddingObj> getData() {
        return data;
    }

    public void setData(List<EmbeddingObj> data) {
        this.data = data;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}

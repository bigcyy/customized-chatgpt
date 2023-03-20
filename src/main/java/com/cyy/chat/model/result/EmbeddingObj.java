package com.cyy.chat.model.result;

import java.util.List;

/**
 * @author CYY
 * @date 2023年03月10日 下午10:58
 * @description
 */
public class EmbeddingObj {
    private String object;
    private Integer index;
    private List<Float> embedding;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Float> getEmbedding() {
        return embedding;
    }

    public void setEmbedding(List<Float> embedding) {
        this.embedding = embedding;
    }
}

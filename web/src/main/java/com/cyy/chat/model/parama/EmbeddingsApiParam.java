package com.cyy.chat.model.parama;

/**
 * @author CYY
 * @date 2023年03月10日 下午9:49
 * @description
 */
public class EmbeddingsApiParam {
    private String model = "text-embedding-ada-002";
    private String input;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}

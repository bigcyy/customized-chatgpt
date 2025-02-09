package com.cyy.chat.model.result;

import com.google.api.Usage;

import java.util.List;

/**
 * @author CYY
 * @date 2023年02月11日 下午6:47
 * @description chatGpt官方api返回结果封装
 */
public class ChatGptApiResult {
    private String id;
    private String object;
    private Long created;
    private String model;
    private Usage usage;

    private List<Choices> choices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Choices> getChoices() {
        return choices;
    }

    public void setChoices(List<Choices> choices) {
        this.choices = choices;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
}

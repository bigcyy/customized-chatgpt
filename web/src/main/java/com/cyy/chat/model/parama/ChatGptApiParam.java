package com.cyy.chat.model.parama;

import java.util.List;

/**
 * @author CYY
 * @date 2023年02月11日 下午8:36
 * @description chat模式的api参数，聊天模式官方推荐参数
 */
public class ChatGptApiParam {
    private String model = "gpt-3.5-turbo";
    private List<Message> messages;
    private double temperature = 1;
    private double top_p = 1;
    private int n = 1;
    private boolean stream = false;
    private String[] stop = null;
    private int max_tokens = 3072;
    private double presence_penalty = 0;
    private double frequency_penalty = 0;

    public ChatGptApiParam() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTop_p() {
        return top_p;
    }

    public void setTop_p(double top_p) {
        this.top_p = top_p;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isStream() {
        return stream;
    }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public String[] getStop() {
        return stop;
    }

    public void setStop(String[] stop) {
        this.stop = stop;
    }

    public int getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(int max_tokens) {
        this.max_tokens = max_tokens;
    }

    public double getPresence_penalty() {
        return presence_penalty;
    }

    public void setPresence_penalty(double presence_penalty) {
        this.presence_penalty = presence_penalty;
    }

    public double getFrequency_penalty() {
        return frequency_penalty;
    }

    public void setFrequency_penalty(double frequency_penalty) {
        this.frequency_penalty = frequency_penalty;
    }
}

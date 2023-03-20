package com.cyy.chat.model.result;

import com.cyy.chat.model.parama.Message;

/**
 * @author CYY
 * @date 2023年02月11日 下午6:52
 * @description chatGpt官方api返回结果封装
 */
public class Choices {
    private Message message;
    private int index;
    private String finish_reason;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}

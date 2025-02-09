package com.cyy.chat.model;

import com.cyy.chat.model.parama.ChatGptApiParam;
import com.cyy.chat.model.result.ChatGptApiResult;
import com.cyy.chat.model.parama.Message;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CYY
 * @date 2023年02月11日 下午5:49
 * @description
 */
@Component
public class ChatGptModel {
    private final Message systemMsg = new Message("system",
            "Use the text provided to form your answer, but avoid copying the text verbatim and avoid mentioning that you referenced the text in your answer. " +
                    "Try to use your own words when possible. Keep your answer under 5 sentences." +
                    "If the question is not relevant to the text provided you just need to reply in Chinese: \"Sorry, I can't provide you with the answer to {question}.\" " +
                    "If not ask please chat normally." +
                    "Be accurate, helpful, concise, and clear.");
    @Autowired
    private OkHttpClient openAiHttpClient;
    private final ChatGptApiParam chatGptApiParam = new ChatGptApiParam();

    /**
     * 向openAi发送请求获取问题答案
     * @param question 问题
     * @return 答案，超时或者其他异常返回默认信息
     */
    public String doChat(String apiKey,String question,List<String> passages){
        /*
            将查找出来的文章集合处理成字符串
         */
        StringBuilder builder = new StringBuilder();
        for(int i = 1;i<= passages.size();i++){
            builder.append(i).append(passages.get(i-1)).append("\n");
        }
        /*
            处理需要请求的信息
         */
        List<Message> messageList = new ArrayList<>();
        messageList.add(this.systemMsg);
        String msg = "Refer to the text below to answer in simplified Chinese:\"{%s}\"\n\"text:{%s}\"";
        String format = String.format(msg, question, builder);
        Message userMessage = new Message("user", format);
        messageList.add(userMessage);
        this.chatGptApiParam.setMessages(messageList);  //加入请求体中
        Gson gson = new Gson();
        String json = gson.toJson(this.chatGptApiParam);
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization",apiKey)
                .post(requestBody).build();
        Message gptMessage;
        try (Response response = this.openAiHttpClient.newCall(request).execute()) {
            if(response.code() == 200){
                ChatGptApiResult chatGptApiResult = gson.fromJson(response.body().string(), ChatGptApiResult.class);
                //取出gpt回应信息
                gptMessage = chatGptApiResult.getChoices().get(0).getMessage();
            }else {
                return "chatGpt出错了,错误码:"+response.code();
                //todo 频繁问答对应策略
            }
        } catch (SocketTimeoutException e){
            e.printStackTrace();
            return "网络超时了，稍后再向我提问吧。";
        }catch (IOException e) {
            e.printStackTrace();
            return "chatGpt出错了,try again......";
        }
        return gptMessage.getContent();
    }

}

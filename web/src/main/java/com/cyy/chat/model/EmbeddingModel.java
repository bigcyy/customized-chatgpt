package com.cyy.chat.model;

import com.cyy.chat.model.parama.EmbeddingsApiParam;
import com.cyy.chat.model.result.EmbeddingsApiResult;
import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author CYY
 * @date 2023年03月11日 下午9:00
 * @description
 */
@Component
public class EmbeddingModel {
    @Autowired
    private OkHttpClient openAiHttpClient;
    private EmbeddingsApiParam embeddingsApiParam = new EmbeddingsApiParam();

    /**
     * 请求获取Embeddings，请求出错返回null
     * @param apiKey openAIKey
     * @param msg 需要Embeddings的信息
     * @return 为null则请求失败，反之放回正确结果
     */
    public EmbeddingsApiResult doEmbedding(String apiKey, String msg){
        this.embeddingsApiParam.setInput(msg);
        Gson gson = new Gson();
        String json = gson.toJson(this.embeddingsApiParam);
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/embeddings")
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization", apiKey)
                .post(requestBody).build();
        EmbeddingsApiResult embeddingsApiResult = null;
        try(Response response = openAiHttpClient.newCall(request).execute()){
            if(response.code() == 200){
                embeddingsApiResult = gson.fromJson(response.body().string(), EmbeddingsApiResult.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return embeddingsApiResult;
    }
}

package com.cyy.chat.service.impl;

import com.cyy.chat.model.ChatGptModel;
import com.cyy.chat.model.EmbeddingModel;
import com.cyy.chat.model.result.EmbeddingsApiResult;
import com.cyy.chat.pojo.PDFData;
import com.cyy.chat.service.IChatService;
import io.milvus.client.MilvusClient;
import io.milvus.common.clientenum.ConsistencyLevelEnum;
import io.milvus.grpc.SearchResults;
import io.milvus.param.R;
import io.milvus.param.collection.LoadCollectionParam;
import io.milvus.param.collection.ReleaseCollectionParam;
import io.milvus.param.dml.InsertParam;
import io.milvus.param.dml.SearchParam;
import io.milvus.response.SearchResultsWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CYY
 * @date 2023年03月11日 下午7:31
 * @description
 */
@Service
public class ChatServiceImpl implements IChatService {
    @Autowired
    private MilvusClient milvusClient;
    @Autowired
    private ChatGptModel chatGptModel;
    @Autowired
    private EmbeddingModel embeddingModel;
    public String toChat(String key,String question){
        EmbeddingsApiResult embedding = embeddingModel.doEmbedding(key,question);
        if(embedding == null) return "请求失败！";
        List<Float> vector = embedding.getData().get(0).getEmbedding();
        List<PDFData> searchResult = search(Arrays.asList(vector));
        List<String> contents = new ArrayList<>();
        for(PDFData data:searchResult){
            contents.add(data.getContent());
        }
        String ans = chatGptModel.doChat(key,question, contents);
        return ans;
    }
    public List<PDFData> search(List<List<Float>> search_vectors){
        milvusClient.loadCollection(
                LoadCollectionParam.newBuilder()
                        .withCollectionName("pdf_data")
                        .build()
        );
        final Integer SEARCH_K = 4;                       // TopK
        final String SEARCH_PARAM = "{\"nprobe\":10}";    // Params
        List<String> ids = Arrays.asList("id");
        List<String> contents = Arrays.asList("content");
        List<String> contentWordCounts = Arrays.asList("content_word_count");
        SearchParam searchParam = SearchParam.newBuilder()
                .withCollectionName("pdf_data")
                .withConsistencyLevel(ConsistencyLevelEnum.STRONG)
                .withOutFields(ids)
                .withOutFields(contents)
                .withOutFields(contentWordCounts)
                .withTopK(SEARCH_K)
                .withVectors(search_vectors)
                .withVectorFieldName("content_vector")
                .withParams(SEARCH_PARAM)
                .build();
        R<SearchResults> respSearch = milvusClient.search(searchParam);
        List<PDFData> pdfDataList = new ArrayList<>();
        if(respSearch.getStatus() == R.Status.Success.getCode()){
            //respSearch.getData().getStatus() == R.Status.Success
            SearchResults resp = respSearch.getData();
            if(!resp.hasResults()){ //判断是否查到结果
                return new ArrayList<>();
            }
            for (int i = 0; i < search_vectors.size(); ++i) {
                SearchResultsWrapper wrapperSearch = new SearchResultsWrapper(resp.getResults());
                List<Long> id = (List<Long>) wrapperSearch.getFieldData("id", 0);
                List<String> content = (List<String>) wrapperSearch.getFieldData("content", 0);
                List<Integer> contentWordCount = (List<Integer>) wrapperSearch.getFieldData("content_word_count", 0);
                PDFData pdfData = new PDFData(id.get(0),content.get(0),contentWordCount.get(0));
                pdfDataList.add(pdfData);

            }

        }
        milvusClient.releaseCollection(
                ReleaseCollectionParam.newBuilder()
                        .withCollectionName("pdf_data")
                        .build());
        return pdfDataList;
    }
    public void save(String key,List<String> sentenceList){
        List<Integer> contentWordCount = new ArrayList<>();
        List<List<Float>> contentVector = new ArrayList<>();
        for(String str : sentenceList){
            contentWordCount.add(str.length());
            EmbeddingsApiResult embedding = embeddingModel.doEmbedding(key,str);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(embedding == null){
                return;
            }
            contentVector.add(embedding.getData().get(0).getEmbedding());
        }

        List<InsertParam.Field> fields = new ArrayList<>();
        fields.add(new InsertParam.Field("content", sentenceList));
        fields.add(new InsertParam.Field("content_word_count", contentWordCount));
        fields.add(new InsertParam.Field("content_vector", contentVector));

        InsertParam insertParam = InsertParam.newBuilder()
                .withCollectionName("pdf_data")
                .withFields(fields)
                .build();
        //插入数据
        milvusClient.insert(insertParam);
    }

}

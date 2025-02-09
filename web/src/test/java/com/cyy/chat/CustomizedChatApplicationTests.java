package com.cyy.chat;

import io.milvus.client.MilvusServiceClient;
import io.milvus.grpc.DataType;
import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import io.milvus.param.collection.CreateCollectionParam;
import io.milvus.param.collection.DropCollectionParam;
import io.milvus.param.collection.FieldType;
import io.milvus.param.index.CreateIndexParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomizedChatApplicationTests {
    @Autowired
    MilvusServiceClient milvusClient;

    @Test
    void prepare() {
        dropCollection(milvusClient);
        createCollection(milvusClient);
        buildIndex(milvusClient);
    }
    void buildIndex(MilvusServiceClient client){
        final String INDEX_PARAM = "{\"nlist\":1024}";
        client.createIndex(
                CreateIndexParam.newBuilder()
                        .withCollectionName("pdf_data")
                        .withFieldName("content_vector")
                        .withIndexType(IndexType.IVF_FLAT)
                        .withMetricType(MetricType.L2)
                        .withExtraParam(INDEX_PARAM)
                        .withSyncMode(Boolean.FALSE)
                        .build()
        );
    }
    void dropCollection(MilvusServiceClient client){
        client.dropCollection(
                DropCollectionParam.newBuilder()
                        .withCollectionName("pdf_data")
                        .build()
        );
    }
    void createCollection(MilvusServiceClient client){
        FieldType fieldType1 = FieldType.newBuilder()
                .withName("id")
                .withDataType(DataType.Int64)
                .withPrimaryKey(true)
                .withAutoID(true)
                .build();
        FieldType fieldType2 = FieldType.newBuilder()
                .withName("content_word_count")
                .withDataType(DataType.Int32)
                .build();
        FieldType fieldType3 = FieldType.newBuilder()
                .withName("content")
                .withDataType(DataType.VarChar)
                .withMaxLength(1024)
                .build();
        FieldType fieldType4 = FieldType.newBuilder()
                .withName("content_vector")
                .withDataType(DataType.FloatVector)
                .withDimension(1536)
                .build();
        CreateCollectionParam createCollectionReq = CreateCollectionParam.newBuilder()
                .withCollectionName("pdf_data")
                .withShardsNum(4)
                .addFieldType(fieldType1)
                .addFieldType(fieldType2)
                .addFieldType(fieldType3)
                .addFieldType(fieldType4)
                .build();
        client.createCollection(createCollectionReq);
    }
}

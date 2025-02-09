package com.cyy.chat.service;

import com.cyy.chat.pojo.PDFData;

import java.util.List;

/**
 * @author CYY
 * @date 2023年03月11日 下午7:30
 * @description
 */
public interface IChatService {
    String toChat(String key, String question);
    void save(String key, List<String> sentenceList);
    List<PDFData> search(List<List<Float>> search_vectors);
}

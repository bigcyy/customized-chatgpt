package com.cyy.chat.pojo;

import java.util.List;

/**
 * @author CYY
 * @date 2023年03月11日 下午10:08
 * @description
 */
public class PDFData {
    private Long id;
    private String content;
    private Integer contentWordCount;

    public PDFData(Long id, String content, Integer contentWordCount) {
        this.id = id;
        this.content = content;
        this.contentWordCount = contentWordCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentWordCount() {
        return contentWordCount;
    }

    public void setContentWordCount(Integer contentWordCount) {
        this.contentWordCount = contentWordCount;
    }

}

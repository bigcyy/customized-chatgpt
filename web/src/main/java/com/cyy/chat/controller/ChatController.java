package com.cyy.chat.controller;

import com.cyy.chat.DocParser.AbstractParser;
import com.cyy.chat.service.IChatService;
import com.cyy.chat.DocParser.PdfParse;
import com.cyy.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author CYY
 * @date 2023年03月11日 下午7:22
 * @description
 */
@RestController
@RequestMapping("/api")
@Tag(name = "对话引擎", description = "管理对话相关的内容")
public class ChatController {
    @Autowired
    private IChatService chatService;


    private final String openAIkey = "openAI_key";
    @PutMapping("/save")
    public void saveKey(@RequestParam String key, HttpServletRequest request){
        request.getSession().setAttribute(openAIkey,key);
    }
    @GetMapping("/chat")
    @Operation(
            summary = "对话接口",
            description = "根据用户输入的问题返回对应的回答"
    )
    public R chat(
            @Parameter(description = "用户的问题",required = true,example = "你能做什么？")
            @RequestParam String question){
        return R.ok().data("msg","temp");
    }
    @PostMapping("/upload")
    public void upload(MultipartFile file, HttpServletRequest request) throws Exception {
        AbstractParser pdfParse = new PdfParse();
        String key = (String) request.getSession().getAttribute(openAIkey);
        List<String> sentenceList = pdfParse.parse(file.getInputStream());
        chatService.save(key,sentenceList);
    }
}

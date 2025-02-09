package com.cyy.chat.controller;

import com.cyy.chat.DocParser.AbstractParser;
import com.cyy.chat.service.IChatService;
import com.cyy.chat.DocParser.PdfParse;
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
public class ChatController {
    @Autowired
    private IChatService chatService;

    private final String openAIkey = "openAI_key";
    @PutMapping("/save")
    public void saveKey(@RequestParam String key, HttpServletRequest request){
        request.getSession().setAttribute(openAIkey,key);
    }
    @GetMapping("/chat")
    public String chat(@RequestParam String question,HttpServletRequest request){
        String key = (String) request.getSession().getAttribute(openAIkey);
        if(key == null || "".equals(key)) return "请先设置ApiKey。";
        return chatService.toChat(key, question);
    }
    @PostMapping("/upload")
    public void upload(MultipartFile file, HttpServletRequest request) throws Exception {
        AbstractParser pdfParse = new PdfParse();
        String key = (String) request.getSession().getAttribute(openAIkey);
        List<String> sentenceList = pdfParse.parse(file.getInputStream());
        chatService.save(key,sentenceList);
    }
}

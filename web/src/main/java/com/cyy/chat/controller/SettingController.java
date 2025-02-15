package com.cyy.chat.controller;

import com.cyy.chat.provider.ModelFactory;
import com.cyy.common.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/setting")
@Tag(name = "设置模块", description = "设置模块相关接口")
public class SettingController {

    @Resource
    private ModelFactory modelFactory;

    @GetMapping("/{provider}/models")
    @Operation(
            summary = "获取指定提供商支持的模型列表",
            description = "获取指定提供商支持的模型列表"
    )
    public R listModels(@PathVariable @Parameter(description = "提供商名称", required = true, example = "OpenAI") String provider){
        return R.ok().data("models", modelFactory.getProvider(provider).listSupportedModels());
    }

    @GetMapping("/providers")
    @Operation(
            summary = "获取支持的提供商列表",
            description = "获取支持的提供商列表，包括模型提供商名称、模型提供商 icon 路径"
    )
    public R listProviders(){
        return R.ok().data("providers", modelFactory.listProviders());
    }
}

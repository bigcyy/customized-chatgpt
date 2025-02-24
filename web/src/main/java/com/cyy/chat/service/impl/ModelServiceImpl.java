package com.cyy.chat.service.impl;

import com.cyy.chat.model.Model;
import com.cyy.chat.dao.ModelMapper;
import com.cyy.chat.provider.ModelFactory;
import com.cyy.chat.service.IModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyy.common.exception.ClientGlobalException;
import com.cyy.common.exception.SystemGlobalException;
import jakarta.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CYY
 * @since 2025-02-20
 */
@Service
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

    @Resource
    private ModelFactory modelFactory;


    @Override
    public Boolean checkModel(Model model) {
        return modelFactory.getProvider(model.getProvider())
                .checkModelConnect(model.getApiUrl(), model.getApiKey(),model.getModelName());
    }

    @Override
    public Boolean checkModel(String provider, String baseUrl, String apiKey, String modelId) {
        return modelFactory.getProvider(provider)
                .checkModelConnect(baseUrl, apiKey,modelId);
    }

    @Override
    public void saveModel(Model model) {
        // 1. 检查模型是否可以连接
        Boolean canConnect = checkModel(model);
        if(!canConnect){
            throw new SystemGlobalException("模型无法连接");
        }
        // todo 2. 加密 api key
        try {
            // 3. 保存
            this.save(model);
        } catch (DuplicateKeyException e){ // MySQL 的显示名重复
            throw new ClientGlobalException("模型名称重复");
        }
    }

}

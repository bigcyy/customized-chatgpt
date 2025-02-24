package com.cyy.chat.service;

import com.cyy.chat.model.Model;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CYY
 * @since 2025-02-20
 */
public interface IModelService extends IService<Model> {

    /**
     * 检查模型是否可以连接
     */
    Boolean checkModel(Model model);

    /**
     * 检查模型是否可以连接
     */
    Boolean checkModel(String provider,String baseUrl,String apiKey, String modelId);

    /**
     * 保存模型
     */
    void saveModel(Model model);

}

package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.ImportantInformationEntity;

import java.util.Map;

/**
 * 资料下载表
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2023-01-02 22:37:46
 */
public interface ImportantInformationService extends IService<ImportantInformationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


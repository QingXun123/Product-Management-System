package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.InformationDownloadEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 21:54:31
 */
public interface InformationDownloadService extends IService<InformationDownloadEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<InformationDownloadEntity> findByMain();

    List<InformationDownloadEntity> findByBeforeId(Integer beforeId);
}


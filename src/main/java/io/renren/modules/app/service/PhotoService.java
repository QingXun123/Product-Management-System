package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.entity.PhotoEntity;

import java.util.Map;

/**
 * 页面图片表
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-30 01:38:31
 */
public interface PhotoService extends IService<PhotoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R RUpdate(PhotoEntity photo);

    R RSave(PhotoEntity photo);
}


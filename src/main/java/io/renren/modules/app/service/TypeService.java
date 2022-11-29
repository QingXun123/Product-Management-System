package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.entity.type;

import java.util.List;
import java.util.Map;

public interface TypeService extends IService<type> {
    PageUtils queryPage(Map<String, Object> params);



}

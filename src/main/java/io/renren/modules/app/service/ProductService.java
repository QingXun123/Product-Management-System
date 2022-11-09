package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.ProductEntity;

import java.util.Map;

/**
 * 
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 19:46:44
 */
public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.sys.entity.ProductEntity;
import io.renren.modules.sys.service.impl.ProductServiceImpl;

import java.util.List;
import java.util.Map;

public interface ProductService extends IService<ProductEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

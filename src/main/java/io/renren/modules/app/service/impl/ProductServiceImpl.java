package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.ProductDao;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.service.ProductService;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductEntity> page = this.page(
                new Query<ProductEntity>().getPage(params),
                new QueryWrapper<ProductEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<ProductEntity> findByHot() {
        LambdaQueryWrapper<ProductEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ProductEntity::getHot, 1);
        return productDao.selectList(lqw);
    }

    @Override
    public List<ProductEntity> findBySlideshow() {
        LambdaQueryWrapper<ProductEntity> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ProductEntity::getTypeId, 1);
        return productDao.selectList(lqw);
    }

}
package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.modules.app.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.InformationDownloadDao;
import io.renren.modules.app.entity.InformationDownloadEntity;
import io.renren.modules.app.service.InformationDownloadService;


@Service("informationDownloadService")
public class InformationDownloadServiceImpl extends ServiceImpl<InformationDownloadDao, InformationDownloadEntity> implements InformationDownloadService {

    @Autowired
    private InformationDownloadDao informationDownloadDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InformationDownloadEntity> page = this.page(
                new Query<InformationDownloadEntity>().getPage(params),
                new QueryWrapper<InformationDownloadEntity>()
        );

        return new PageUtils(page);
    }

}
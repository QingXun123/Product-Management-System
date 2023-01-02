package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.app.dao.ImportantInformationDao;
import io.renren.modules.app.entity.ImportantInformationEntity;
import io.renren.modules.app.service.ImportantInformationService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("importantInformationService")
public class ImportantInformationServiceImpl extends ServiceImpl<ImportantInformationDao, ImportantInformationEntity> implements ImportantInformationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ImportantInformationEntity> page = this.page(
                new Query<ImportantInformationEntity>().getPage(params),
                new LambdaQueryWrapper<ImportantInformationEntity>().like(ImportantInformationEntity::getName, params.get("key"))
        );

        return new PageUtils(page);
    }

}
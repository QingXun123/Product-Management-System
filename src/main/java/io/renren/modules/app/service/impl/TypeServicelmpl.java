package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.app.dao.TypeDao;
import io.renren.modules.app.entity.type;
import io.renren.modules.app.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("typeService")
public class TypeServicelmpl extends ServiceImpl<TypeDao, type> implements TypeService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<type> page = this.page(
                new Query<type>().getPage(params),
                new QueryWrapper<type>()
        );

        return new PageUtils(page);
    }
}

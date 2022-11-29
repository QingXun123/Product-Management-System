package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.dao.ProductDao;
import io.renren.modules.app.dao.TypeDao;
import io.renren.modules.app.entity.type;
import io.renren.modules.app.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("typeService")
public class TypeServicelmpl extends ServiceImpl<TypeDao, type> implements TypeService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        return null;
    }
}

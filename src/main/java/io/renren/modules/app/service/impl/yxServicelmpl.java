package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.modules.app.dao.yxDao;
import io.renren.modules.app.entity.yx;
import io.renren.modules.app.service.YxService;
import org.springframework.stereotype.Service;

@Service("yxService")
public class yxServicelmpl extends ServiceImpl<yxDao, yx> implements YxService {

}

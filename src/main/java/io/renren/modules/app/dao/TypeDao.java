package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.type;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TypeDao extends BaseMapper<type> {
}

package io.renren.modules.app.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.type;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-12-22 23:05:45
 */
@Mapper
public interface TypeDao extends BaseMapper<type> {

}

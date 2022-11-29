package io.renren.modules.app.dao;

import io.renren.modules.app.entity.PhotoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 页面图片表
 * 
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-30 01:38:31
 */
@Mapper
public interface PhotoDao extends BaseMapper<PhotoEntity> {
	
}

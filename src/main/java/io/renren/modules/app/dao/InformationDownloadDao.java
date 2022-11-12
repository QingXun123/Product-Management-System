package io.renren.modules.app.dao;

import io.renren.modules.app.entity.InformationDownloadEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 
 * 
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 21:54:31
 */
@Mapper
public interface InformationDownloadDao extends BaseMapper<InformationDownloadEntity> {

}

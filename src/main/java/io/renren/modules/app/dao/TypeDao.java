package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.InformationDownloadEntity;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.entity.type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TypeDao extends BaseMapper<type> {
    @Select("select * from type, product where type.type_id = #{id} and product.type_id = #{id};")
    List<ProductEntity> findtypeid(@Param("id") Integer id);
}

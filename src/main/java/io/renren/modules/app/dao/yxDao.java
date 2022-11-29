package io.renren.modules.app.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.renren.modules.app.entity.yx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface yxDao extends BaseMapper<yx> {
    @Select("select youxiang from yx where youxiang = #{youxiang}")
    String youxiang(@Param("youxiang") String youxiang);

    @Select("select yzm from yx where youxiang = #{youxiang}")
    String yzm(@Param("youxiang") String youxiang);

    @Select("select youxiang from yx where youxiang = #{youxiang}")
    String check(@Param("youxiang") String youxiang);
    @Select("update yx set yzm = #{yzm} where youxiang = #{youxiang}")
    String update(@Param("yzm") String yzm  ,@Param("youxiang") String youxiang);
    @Select("select id from yx where youxiang = #{youxiang}")
    String selet(@Param("youxiang") String youxiang);
}

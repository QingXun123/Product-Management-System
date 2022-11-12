package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("type")
public class type implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 类型ID
     */
    @TableId
    private Integer typeId;
    /**
     * 类型名字
     */
    private String name;

}

package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class yx implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId
    private Integer id;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户邮箱
     */
    private String youxiang;
    /**
     * 用户验证码
     */
    private String yzm;

}


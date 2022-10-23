package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("product")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 产品ID
     */
    @TableId
    private Integer productId;
    /**
     * 产品名字
     */
    private String name;
    /**
     * 产品图片路径
     */
    private String photo;
    /**
     * 热度
     */
    @Value("false")
    private Boolean hot;
}

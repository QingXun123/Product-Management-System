package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 19:46:44
 */
@Data
@TableName("product")
public class ProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 产品id
	 */
	@TableId
	private Integer productId;
	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 产品图片链接
	 */
	private String photo;
	/**
	 * 富文本
	 */
	private String text;
	/**
	 * 热门标识
	 */
	private Integer hot;
	/**
	 * 分类标识
	 */
	private Integer typeId;
	/**
	 * 轮播图标识
	 */
	private Integer slide;

}

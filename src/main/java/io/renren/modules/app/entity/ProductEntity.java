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
	 * 
	 */
	@TableId
	private Integer productId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String photo;
	/**
	 * 
	 */
	private Integer hot;
	private Integer typeId;

}

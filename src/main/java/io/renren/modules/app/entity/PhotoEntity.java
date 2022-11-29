package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 页面图片表
 * 
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-30 01:38:31
 */
@Data
@TableName("photo")
public class PhotoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 图片标识id
	 */
	@TableId
	private Integer id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 图片链接
	 */
	private String photo;

}

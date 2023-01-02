package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 21:54:31
 */
@Data
@TableName("information_download")
public class InformationDownloadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 资料id
	 */
	@TableId
	private Integer id;
	/**
	 * 上一级id
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Integer beforeId;
	/**
	 * 资料名字
	 */
	private String name;
	/**
	 * 链接
	 */
	private String url;
	/**
	 * 富文本
	 */
	private String text;
}

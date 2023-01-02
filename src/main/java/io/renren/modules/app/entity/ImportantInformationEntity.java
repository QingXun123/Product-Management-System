package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资料下载表
 * 
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2023-01-02 22:37:46
 */
@Data
@TableName("important_information")
public class ImportantInformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 资料id
	 */
	@TableId
	private Integer id;
	/**
	 * 资料名字
	 */
	private String name;
	/**
	 * 富文本
	 */
	private String text;

}

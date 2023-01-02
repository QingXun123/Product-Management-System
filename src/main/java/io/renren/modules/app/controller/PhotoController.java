package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.app.entity.PhotoEntity;
import io.renren.modules.app.service.PhotoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 页面图片表
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-30 01:38:31
 */
@RestController
@RequestMapping("app/photo")
@Api(tags = "页面图片")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /**
     * 列表
     */
    @ApiOperation("根据id查询页面图片")
    @GetMapping("/get")
    public R get(@RequestParam("id") Integer id){
        PhotoEntity photo = photoService.getOne(new LambdaQueryWrapper<PhotoEntity>()
                .eq(PhotoEntity::getId, id));
        String url = photo.getPhoto();
        if (url == null) {
            return R.error("图片不存在！");
        }
        return R.ok().put("data", url);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("app:photo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = photoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("app:photo:info")
    public R info(@PathVariable("id") Integer id){
		PhotoEntity photo = photoService.getById(id);

        return R.ok().put("photo", photo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("app:photo:save")
    public R save(@RequestBody PhotoEntity photo){
		return photoService.RSave(photo);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("app:photo:update")
    public R update(@RequestBody PhotoEntity photo){
		return photoService.RUpdate(photo);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("app:photo:delete")
    public R delete(@RequestBody Integer[] ids){
		photoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

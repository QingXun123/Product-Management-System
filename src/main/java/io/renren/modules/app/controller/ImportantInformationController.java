package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.modules.app.entity.InformationDownloadEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.app.entity.ImportantInformationEntity;
import io.renren.modules.app.service.ImportantInformationService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import static io.renren.modules.app.utils.PhotoUtils.PHOTO_URL_REGEX;


/**
 * 资料下载表
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2023-01-02 22:37:46
 */
@RestController
@RequestMapping("app/importantinformation")
@Api(tags = "重要信息")
public class ImportantInformationController {
    @Autowired
    private ImportantInformationService importantInformationService;

    @GetMapping("/imformationdl")
    @ApiOperation("资料下载")
    public R InformationDL() {
        ImportantInformationEntity ide = importantInformationService.getOne(new LambdaQueryWrapper<ImportantInformationEntity>()
                .eq(ImportantInformationEntity::getName, "资料下载"));
        if (ide == null) {
            return R.error("页面不存在！");
        }
        return R.ok().put("data", ide.getText().replaceAll(PHOTO_URL_REGEX, ""));
    }

    @GetMapping
    @ApiOperation("关于我们")
    public R aboutUs() {
        ImportantInformationEntity ide = importantInformationService.getOne(new LambdaQueryWrapper<ImportantInformationEntity>()
                .eq(ImportantInformationEntity::getName, "关于我们"));
        if (ide == null) {
            return R.error("页面不存在！");
        }
        return R.ok().put("data", ide.getText().replaceAll(PHOTO_URL_REGEX, ""));
    }

    @GetMapping("/shop")
    @ApiOperation("淘宝链接")
    public R shopURL() {
        ImportantInformationEntity ide = importantInformationService.getOne(new LambdaQueryWrapper<ImportantInformationEntity>()
                .eq(ImportantInformationEntity::getName, "淘宝链接"));
        if (ide == null) {
            return R.error("页面不存在！");
        }
        String text = ide.getText();
        text = text.substring(3, text.length() - 4);
        return R.ok().put("data", text);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
//    @RequiresPermissions("app:importantinformation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = importantInformationService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
//    @RequiresPermissions("app:importantinformation:info")
    public R info(@PathVariable("id") Integer id){
		ImportantInformationEntity importantInformation = importantInformationService.getById(id);

        return R.ok().put("importantInformation", importantInformation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
//    @RequiresPermissions("app:importantinformation:save")
    public R save(@RequestBody ImportantInformationEntity importantInformation){
		importantInformationService.save(importantInformation);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
//    @RequiresPermissions("app:importantinformation:update")
    public R update(@RequestBody ImportantInformationEntity importantInformation){
		importantInformationService.updateById(importantInformation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
//    @RequiresPermissions("app:importantinformation:delete")
    public R delete(@RequestBody Integer[] ids){
		importantInformationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

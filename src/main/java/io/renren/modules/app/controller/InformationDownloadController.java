package io.renren.modules.app.controller;

import java.io.File;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.modules.app.dao.InformationDownloadDao;
import io.renren.modules.app.entity.InformationListEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.app.entity.InformationDownloadEntity;
import io.renren.modules.app.service.InformationDownloadService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import static io.lettuce.core.GeoArgs.Unit.m;
import static io.renren.modules.app.utils.PhotoUtils.PHOTO_URL_REGEX;


/**
 * 
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 21:54:31
 */
@RestController
@RequestMapping("app/informationdownload")
@Api(tags = "资料下载")
public class InformationDownloadController {
    @Autowired
    private InformationDownloadService informationDownloadService;

    @GetMapping("/text/{id}")
    @ApiOperation("根据产品id拿到富文本数据")
    public R get(@RequestParam("id") Integer id) {
        InformationDownloadEntity ide = informationDownloadService.getOne(new LambdaQueryWrapper<InformationDownloadEntity>()
                .eq(InformationDownloadEntity::getId, id));
        if (ide == null) {
            return R.error("页面不存在！");
        }
        return R.ok().put("data", ide.getText().replaceAll(PHOTO_URL_REGEX, ""));
    }

    @GetMapping
    @ApiOperation("关于我们")
    public R aboutUs() {
        InformationDownloadEntity ide = informationDownloadService.getOne(new LambdaQueryWrapper<InformationDownloadEntity>()
                .eq(InformationDownloadEntity::getName, "关于我们"));
        if (ide == null) {
            return R.error("页面不存在！");
        }
        return R.ok().put("data", ide.getText().replaceAll(PHOTO_URL_REGEX, ""));
    }

    /**
     * 返回资料下载列表数据
     */
    @GetMapping("/information")
    @ApiOperation("资料下载列表")
    public R find() {
        List<InformationListEntity> ileL = new ArrayList<>();
        List<InformationDownloadEntity> ide = informationDownloadService.list(new LambdaQueryWrapper<InformationDownloadEntity>()
                .isNull(InformationDownloadEntity::getBeforeId));
        for (InformationDownloadEntity i : ide) {
            List<InformationDownloadEntity> ideL = informationDownloadService.list(new LambdaQueryWrapper<InformationDownloadEntity>()
                    .eq(InformationDownloadEntity::getBeforeId, i.getId()));
            for(InformationDownloadEntity ii : ideL) {
                if (ii.getText() != null)
                    ii.setText(ii.getText().replaceAll(PHOTO_URL_REGEX, ""));
            }
            ileL.add(new InformationListEntity(i, ideL));
        }

        return R.ok().put("data", ileL);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("app:informationdownload:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = informationDownloadService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("app:informationdownload:info")
    public R info(@PathVariable("id") Integer id){
		InformationDownloadEntity informationDownload = informationDownloadService.getById(id);

        return R.ok().put("informationDownload", informationDownload);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("app:informationdownload:save")
    public R save(@RequestBody InformationDownloadEntity informationDownload){
		informationDownloadService.save(informationDownload);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:informationdownload:update")
    public R update(@RequestBody InformationDownloadEntity informationDownload){
		informationDownloadService.updateById(informationDownload);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:informationdownload:delete")
    public R delete(@RequestBody Integer[] ids){
		informationDownloadService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}

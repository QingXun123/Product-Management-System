package io.renren.modules.app.controller;

import java.io.File;
import java.util.*;

import io.renren.modules.app.dao.InformationDownloadDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.app.entity.InformationDownloadEntity;
import io.renren.modules.app.service.InformationDownloadService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 21:54:31
 */
@RestController
@RequestMapping("app/informationdownload")
@Api("资料下载接口")
public class InformationDownloadController {
    @Autowired
    private InformationDownloadService informationDownloadService;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public R upload(@RequestParam("file") MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        Date date = new Date();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = date.getTime() + suffixName;
//        File file1 = new File();
//        file1.mkdirs();
        //指定本地文件夹存储
        String filePath = "/dist/upload/";
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath+fileName));
            String tarpath = "tar -xvf"+ fileName+"-/dist/upload";
            Process process = Runtime.getRuntime().exec(tarpath);
            System.out.println(process);

            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("400");
        }
    }

//    /**
//     * 返回资料下载列表数据
//     */
//    @GetMapping("/information")
//    @ApiOperation("列表")
//    public R find() {
//        List<List<InformationDownloadEntity>> iiList
//
//        return R.ok().put("main", informationDownloadService.findByMain())
//                .put("before", informationDownloadService.findByBefore());
//    }

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

package io.renren.modules.app.controller;

import java.io.File;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.modules.app.dao.InformationDownloadDao;
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
    public R file(@RequestParam("file") MultipartFile multipartFile, @RequestParam("id") Integer id){
        //判断文件是否为空 isEmpty
        if (multipartFile == null){
            return R.error("文件为空");
        }
        //获取文件的原名称 getOriginalFilename
        String OriginalFilename = multipartFile.getOriginalFilename();
        //获取时间戳和文件的扩展名，拼接成一个全新的文件名； 用时间戳来命名是为了避免文件名冲突
        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //定义文件存放路径
        String filePath = "E:\\ljtImages\\check";
        //新建一个目录（文件夹）
        File dest = new File(filePath+"\\"+fileName);
        //判断filePath目录是否存在，如不存在，就新建一个
        if (!dest.getParentFile().canExecute()){
            dest.getParentFile().mkdirs(); //新建一个目录
        }
        try {
            //文件输出
            multipartFile.transferTo(dest);
        }
        catch ( Exception e) {
            e.printStackTrace();
            //拷贝失败要有提示
            return R.error("上传失败");
        }
        InformationDownloadEntity ide = informationDownloadService.getOne(new LambdaQueryWrapper<InformationDownloadEntity>()
                .eq(InformationDownloadEntity::getId, id));
        ide.setUrl(filePath+"\\"+fileName);
        informationDownloadService.updateById(ide);
        return R.ok().put("data", fileName);
    }

    /**
     * 返回资料下载列表数据
     */
    @GetMapping("/information")
    @ApiOperation("列表")
    public R find() {
        HashMap<InformationDownloadEntity, List<InformationDownloadEntity>> m = new HashMap<>();
        List<InformationDownloadEntity> ide = informationDownloadService.list(new LambdaQueryWrapper<InformationDownloadEntity>()
                .isNull(InformationDownloadEntity::getBeforeId));
        for (InformationDownloadEntity i : ide) {
            List<InformationDownloadEntity> ideBefore = informationDownloadService.list(new LambdaQueryWrapper<InformationDownloadEntity>()
                    .eq(InformationDownloadEntity::getBeforeId, i.getId()));
            m.put(i, ideBefore);
        }

        return R.ok().put("data", m);
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

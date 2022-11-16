package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.utils.R;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("app/upload")
@Api(tags = "文件上传")
public class FilesUpload {

    @Autowired
    private ProductService productService;

    @PostMapping("/productphoto")
    @ApiOperation("上传产品图片")
    public R file(@RequestParam("file") MultipartFile multipartFile, @RequestParam("productid") Integer id){
        //判断文件是否为空 isEmpty
        if (multipartFile == null){
            return R.error("文件为空");
        }
        //获取文件的原名称 getOriginalFilename
        String OriginalFilename = multipartFile.getOriginalFilename();
        //获取时间戳和文件的扩展名，拼接成一个全新的文件名； 用时间戳来命名是为了避免文件名冲突
//        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //以产品id命名来保证不产生多余图片
        String fileName = id + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //定义文件存放路径
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\product\\";
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
        ProductEntity ide = productService.getOne(new LambdaQueryWrapper<ProductEntity>()
                .eq(ProductEntity::getProductId, id));
        ide.setPhoto(filePath+"\\"+fileName);
        productService.updateById(ide);
        return R.ok().put("data", filePath + fileName);
    }
}

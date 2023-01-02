package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.app.dao.ProductDao;
import io.renren.modules.app.entity.PhotoEntity;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.service.ProductService;
import io.renren.modules.app.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

import static io.renren.modules.app.utils.PhotoUtils.PHOTO_URL;


@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductDao, ProductEntity> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProductEntity> page = this.page(
                new Query<ProductEntity>().getPage(params),
                new LambdaQueryWrapper<ProductEntity>().like(ProductEntity::getName, params.get("key"))
        );

        return new PageUtils(page);
    }

    @Override
    public R RUpdate(ProductEntity product) {
        if (product.getPhoto().substring(0, 1).equals("/")) {
            product.setPhoto(getOne(new LambdaQueryWrapper<ProductEntity>()
                    .eq(ProductEntity::getProductId, product.getProductId())).getPhoto());
            updateById(product);
        } else {
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(product.getPhoto());
            product.setPhoto(null);
            updateById(product);
            upFile(file, product.getProductId());
        }

        return R.ok();
    }

    @Override
    public R RSave(ProductEntity product) {
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(product.getPhoto());
        product.setPhoto(null);
        save(product);
        upFile(file, product.getProductId());

        return R.ok();
    }

    private void upFile(MultipartFile multipartFile, Integer id) {
        //判断文件是否为空 isEmpty
        if (multipartFile == null){
            return;
        }
        //获取文件的原名称 getOriginalFilename
        String OriginalFilename = multipartFile.getOriginalFilename();
        //获取时间戳和文件的扩展名，拼接成一个全新的文件名； 用时间戳来命名是为了避免文件名冲突
//        String fileName = System.currentTimeMillis()+"."+OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //以产品id命名来保证不产生多余图片
        String fileName = id + "." + OriginalFilename.substring(OriginalFilename.lastIndexOf(".")+1);
        //定义文件存放路径
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\product";
        //新建一个目录（文件夹）
        System.out.println("\n\n\n\n"+filePath+"\\"+fileName+"\n\n\n\n");
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
            return;
        }
        ProductEntity ide = getOne(new LambdaQueryWrapper<ProductEntity>()
                .eq(ProductEntity::getProductId, id));
        String url = PHOTO_URL + "product/" + fileName;
        ide.setPhoto(url);
        updateById(ide);
    }

}
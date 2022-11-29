package io.renren.modules.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.utils.R;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.utils.BASE64DecodedMultipartFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.app.dao.PhotoDao;
import io.renren.modules.app.entity.PhotoEntity;
import io.renren.modules.app.service.PhotoService;
import org.springframework.web.multipart.MultipartFile;

import static io.renren.modules.app.utils.PhotoUtils.PHOTO_URL;


@Service("photoService")
public class PhotoServiceImpl extends ServiceImpl<PhotoDao, PhotoEntity> implements PhotoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PhotoEntity> page = this.page(
                new Query<PhotoEntity>().getPage(params),
                new QueryWrapper<PhotoEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R RUpdate(PhotoEntity photo) {
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(photo.getPhoto());
        photo.setPhoto(null);
        updateById(photo);
        upFile(file, photo.getId());

        return R.ok();
    }

    @Override
    public R RSave(PhotoEntity photo) {
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipart(photo.getPhoto());
        photo.setPhoto(null);
        save(photo);
        upFile(file, photo.getId());

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
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\photo";
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
            return;
        }
        PhotoEntity ide = getOne(new LambdaQueryWrapper<PhotoEntity>()
                .eq(PhotoEntity::getId, id));
        String url = PHOTO_URL + "photo" + fileName;
        ide.setPhoto(url);
        updateById(ide);
    }

}
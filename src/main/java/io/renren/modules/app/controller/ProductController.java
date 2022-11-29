package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.service.ProductService;
import io.renren.modules.app.utils.BASE64DecodedMultipartFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.renren.modules.app.utils.PhotoUtils.PHOTO_URL_REGEX;


/**
 * 
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 19:46:44
 */
@RestController
@RequestMapping("app/product")
@Api(tags = "产品信息")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation("产品模糊查询")
    @GetMapping("/like/{pname}")
    public R getLike(@RequestParam("pname") String name) {
        List<ProductEntity> list = productService.list(new LambdaQueryWrapper<ProductEntity>().like(ProductEntity::getName, name));
        if (list == null) {
            return R.ok();
        }
        return R.ok().put("data", list);
    }

    @ApiOperation("产品的详细信息")
    @GetMapping("/get/{id}")
    public R getProduct(@RequestParam("id") Integer id) {
        ProductEntity ide = productService.getOne(new LambdaQueryWrapper<ProductEntity>()
                .eq(ProductEntity::getProductId, id));
        ide.setText(ide.getText().replaceAll(PHOTO_URL_REGEX, ""));
        if (ide == null) {
            return R.error("产品不存在！");
        }
        return R.ok().put("data", ide);
    }

    @ApiOperation("轮播图")
    @GetMapping("/slideshow")
    public R slideshow() {
        List<ProductEntity> bySlideshow = productService.list(new LambdaQueryWrapper<ProductEntity>()
                .eq(ProductEntity::getSlide, 1));
        if (bySlideshow.isEmpty())
            return R.error("数据为空！");
        return R.ok().put("data", bySlideshow);
    }

    @ApiOperation("热门产品")
    @GetMapping("/hot")
    public R hot() {
        List<ProductEntity> byHot = productService.list(new LambdaQueryWrapper<ProductEntity>()
                .eq(ProductEntity::getHot, 1));
        if (byHot.isEmpty())
            return R.error("数据为空！");
        return R.ok().put("data", byHot);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("app:product:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = productService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{productId}")
    @RequiresPermissions("app:product:info")
    public R info(@PathVariable("productId") Integer productId){
		ProductEntity product = productService.getById(productId);

        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @ApiOperation("保存")
    @PostMapping("/save")
    public R save(@RequestBody ProductEntity product){
		return productService.RSave(product);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:product:update")
    public R update(@RequestBody ProductEntity product){
        return productService.RUpdate(product);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("app:product:delete")
    public R delete(@RequestBody Integer[] productIds){
		productService.removeByIds(Arrays.asList(productIds));

        return R.ok();
    }

}

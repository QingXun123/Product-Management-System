package io.renren.modules.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.service.ProductService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author QingXun
 * @email 947338658@qq.com
 * @date 2022-11-08 19:46:44
 */
@RestController
@RequestMapping("app/product")
@Api("产品信息")
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation("轮播图")
    @GetMapping("/slideshow")
    public R slideshow() {
        List<ProductEntity> bySlideshow = productService.findBySlideshow();
        if (bySlideshow.isEmpty())
            return R.error("数据为空！");
        return R.ok().put("data", bySlideshow);
    }

    @ApiOperation("热门产品")
    @GetMapping("/hot")
    public R hot() {
        List<ProductEntity> byHot = productService.findByHot();
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
    @RequestMapping("/save")
    @RequiresPermissions("app:product:save")
    public R save(@RequestBody ProductEntity product){
		productService.save(product);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("app:product:update")
    public R update(@RequestBody ProductEntity product){
		productService.updateById(product);

        return R.ok();
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

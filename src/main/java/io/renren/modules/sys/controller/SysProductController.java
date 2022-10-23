package io.renren.modules.sys.controller;


import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.sys.dao.ProductDao;
import io.renren.modules.sys.entity.ProductEntity;
import io.renren.modules.sys.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/product")
@Api("产品信息接口")
public class SysProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/save")
    @RequiresPermissions("sys:product:save")
    @ApiOperation("保存")
    public R save(@RequestBody ProductEntity product) {
        productService.save(product);

        return R.ok();
    }

    @RequestMapping("/delete")
    @RequiresPermissions("sys:product:delete")
    @ApiOperation("删除")
    public R delete(@RequestBody Integer[] ids) {
        productService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/update")
    @RequiresPermissions("sys:product:update")
    @ApiOperation("修改")
    public R update(@RequestBody ProductEntity product) {
        productService.updateById(product);

        return R.ok();
    }

    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:product:info")
    @ApiOperation("查询")
    public R info(@PathVariable("id") Integer id) {
        ProductEntity product = productService.getById(id);

        return R.ok().put("product", product);
    }
}

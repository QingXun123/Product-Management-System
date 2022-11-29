package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.dao.TypeDao;
import io.renren.modules.app.entity.ProductEntity;
import io.renren.modules.app.entity.data;
import io.renren.modules.app.entity.type;
import io.renren.modules.app.service.ProductService;
import io.renren.modules.app.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("app/type")
@Api("类型信息接口")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private ProductService productService;
    @GetMapping("/list")
    @ApiOperation("输出")
    public R list() {
        List<type> data = typeService.list();
        if (data.isEmpty())
            return R.error("数据为空！");
        return R.ok().put("data", data);
    }
    @GetMapping("/typeid")
    @ApiOperation("类型对应产品")
    public R typeid() {
        List<type> data1 = typeService.list();
        List<ProductEntity> data2 = productService.list();
        List<ProductEntity> data3 = new ArrayList<>();
        for(type t: data1) {
            for(ProductEntity product : data2){
                if(t.getTypeId() == product.getTypeId()){
                    data3.add(product);
                }
            }
            t.type(data3);
            data3 = new ArrayList<>();

        }
        if (data1.isEmpty() || data2.isEmpty())
            return R.error("数据为空！");
        return R.ok().put("data1", data1);
    }
    @GetMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody type type) {
        typeService.save(type);
        return R.ok();
    }

    @GetMapping("/delete")
    @ApiOperation("删除")
    public R delete(@RequestBody Integer[] ids) {
        typeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @GetMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody type product) {
        typeService.updateById(product);

        return R.ok();
    }

    @GetMapping("/info/{id}")
    @ApiOperation("查询")
    public R info(@PathVariable("id") Integer id) {
        type product = typeService.getById(id);
        return R.ok().put("product", product);
    }
}


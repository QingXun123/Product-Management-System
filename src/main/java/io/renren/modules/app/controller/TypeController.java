package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.entity.type;
import io.renren.modules.app.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("app/type")
@Api("类型信息接口")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PostMapping("/save")
    @ApiOperation("保存")
    public R save(@RequestBody type type) {
        typeService.save(type);
        return R.ok();
    }

    @RequestMapping("/delete")
    @ApiOperation("删除")
    public R delete(@RequestBody Integer[] ids) {
        typeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/update")
    @ApiOperation("修改")
    public R update(@RequestBody type product) {
        typeService.updateById(product);

        return R.ok();
    }

    @RequestMapping("/info/{id}")
    @ApiOperation("查询")
    public R info(@PathVariable("id") Integer id) {
        type product = typeService.getById(id);
        return R.ok().put("product", product);
    }
}


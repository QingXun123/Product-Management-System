package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.R;
import io.renren.modules.app.dao.yxDao;

import io.renren.modules.app.entity.type;
import io.renren.modules.app.entity.yx;
import io.renren.modules.app.service.YxService;
import io.renren.modules.app.utils.RegexUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static io.renren.modules.app.utils.SendEmailUtil.sendEmail;

@RestController
@RequestMapping("app/login")
@Api("登录接口")
public class login {
    @Autowired
    private yxDao yxDao;
    @Autowired
    private YxService yxService;
    @ApiOperation("登录")
    @PostMapping("/log")
    public R login(@RequestBody yx yx) {
        String youxiang = yx.getYouxiang();
        String youxiang2,yzm2,id;
        if(RegexUtils.isEmailInvalid(youxiang)){
            return R.error("邮箱格式错误!");
        }
        String yzm = yx.getYzm();
        yx v=yxService.getOne(new QueryWrapper<yx>().eq("yzm",yx.getYzm()));

        youxiang2 = yxDao.youxiang(yx.getYouxiang());
        yzm2 = yxDao.yzm(yx.getYouxiang());
        id = yxDao.selet(yx.getYouxiang());
        if(yzm.equals(yzm2)){
            System.out.println(1);
        }
        if(null!=v){
            return R.ok().put("userid",id);
        }
        else{
            return R.error("验证码错误");
        }
    }

    @ApiOperation("发送验证码")
    @PostMapping("/getyzm")
    public R save(@RequestBody yx yx) {
        Integer a;
        if(RegexUtils.isEmailInvalid(yx.getYouxiang())) {
            return R.error("邮箱格式错误!");
        }
        a = sendEmail(yx.getYouxiang());
        System.out.println(yx);
        yx.setYzm(String.valueOf(a));
        yx v=yxService.getOne(new QueryWrapper<yx>().eq("youxiang",yx.getYouxiang()));
        if (null==v) {
            yxService.save(yx);
        }
        else {
            yxDao.update(yx.getYzm(), yx.getYouxiang());
        }

        return R.ok().put("yzm",a);
    }
}
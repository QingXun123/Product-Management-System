package io.renren;

import io.renren.modules.app.utils.SendEmailUtil;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.renren.modules.app.utils.SendEmailUtil.sendEmail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @org.junit.Test
    public void Test() {
        sendEmail("947338658@qq.com");
    }
}

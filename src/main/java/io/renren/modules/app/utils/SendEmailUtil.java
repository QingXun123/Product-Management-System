package io.renren.modules.app.utils;

import java.util.Random;

import org.apache.commons.mail.HtmlEmail;

public class SendEmailUtil {
    private SendEmailUtil() {}

    public static int sendEmail(String emailaddress) {
        try {
            Random random = new Random();
            int code = random.nextInt(9000)+1000;
            HtmlEmail email = new HtmlEmail();// 不用更改
            email.setHostName("smtp.qq.com");// 需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
            email.setCharset("UTF-8");
            email.addTo(emailaddress);// 收件地址

            email.setFrom("3049625601@qq.com", "发件人用户名");// 此处填邮箱地址和用户名,用户名可以任意填写
            //hhhjkkkhhjklj（IMAP/SMTP服务）---这里需要改为你自己
            email.setAuthentication("3049625601@qq.com", "lcbygiydbggcdebh");// 此处填写邮箱地址和客户端授权码

            email.setSubject("邮件名称");// 此处填写邮件名，邮件名可任意填写
            email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);// 此处填写邮件内容

            email.send();
            return code;
        } catch (Exception e) {
            return 0;
        }
    }

}
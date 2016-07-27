package com.xinguan14.controller;
import com.google.code.kaptcha.Constants;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XL on 2016/7/27.
 * 这个类是判断输入的验证码与看到的验证码是否一致
 * 一致则跳到checkSuccess.jsp，不一致则跳到checkFailed.jsp
 */
@Controller
@EnableAutoConfiguration
public class Check {
    @RequestMapping("/check")
    public String forward1(HttpServletRequest request, HttpServletResponse response){
//        获得输入的验证码
        String checkCode = request.getParameter("checkCode");
//        不区分大小写
        checkCode = checkCode.toUpperCase();
//        获得真正的验证码
        String realCheckCode = (String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (checkCode == null||!checkCode.equals(realCheckCode)){

           return "checkFailed";
        }
        else
            return "checkSuccess";
    }
}

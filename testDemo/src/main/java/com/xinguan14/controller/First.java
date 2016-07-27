package com.xinguan14.controller;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
/**
 * Created by XL on 2016/7/25.
 * 主要是画出验证码
 */
@Controller
@EnableAutoConfiguration
//引用配置验证码的文件
@ImportResource( { "classpath*:/checkCode.xml" })
public class First {
//    首页显示
    @RequestMapping(value = "/first",method = RequestMethod.GET)
    public String index(){
        return "first";
    }
    @Autowired
    private Producer captchaProducer;
    @RequestMapping(value = "/getKaptchaImage",method = RequestMethod.GET)
    public ResponseEntity<byte[]> getKaptchaImage(HttpSession httpSession) throws Exception{
        String capText = captchaProducer.createText();
        httpSession.setAttribute(Constants.KAPTCHA_SESSION_KEY,capText);
        BufferedImage bufferedImage = captchaProducer.createImage(capText);
        File file = new File("checkImage");
        ImageIO.write(bufferedImage,"jpg",file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment","checkImg");
        return  new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}

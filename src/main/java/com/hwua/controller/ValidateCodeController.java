package com.hwua.controller;

import com.hwua.util.CodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ValidateCodeController {

    private List<String> readWords(HttpServletRequest request) throws Exception{
        List<String> words = new ArrayList<>();
        //获取ServletContext应用上下文
        ServletContext context = request.getServletContext();
        //获取项目中指定资源的真实路径
        String path = context.getRealPath("/WEB-INF/words.txt");
        try {
            //使用转换流InputStreamReader把字节流按指定的编码转换成字符流,因为FileReader默认使用的时gbk编码
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            System.out.println(words);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }

    @GetMapping("/validateCode")
    public void validateCode(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        List<String> words = readWords(req);
        //调用验证码工具类得到验证码和验证码所生成的图片数据
        Map<String, Object> map = CodeUtil.code(120, 30, 30, words);
        //告知客户端浏览器,写回来的数据是图片
        resp.setContentType("image/jpeg");
        //取出生成的验证码
        String code = (String) map.get("code");
        //把验证码放到Session中
        req.getSession().setAttribute("code",code);
        //以字节输出流的方式输出到浏览器
        ServletOutputStream out = resp.getOutputStream();//获取字节输出流
        //借助ImageIO工具把图片数据转换成二进制的图片,并用字节输出流把二进制数据输出到客户端
        ImageIO.write((RenderedImage) map.get("codePic"),"jpeg",out);
        out.close();
    }


}

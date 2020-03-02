package com.hwua.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CodeUtil {
    /**
     *
     * @param width  图片的宽度
     * @param height 图片的高度
     * @param lineSize  干扰线数量
     * @param words  验证码对应的文字的集合
     * @return
     */
    //返回验证码
    public static Map<String, Object> code(int width, int height, int lineSize,List<String> words) {
        //绘制一张内存中图片,也就是说先在内存中存放绘制图片的数据
        BufferedImage bufferedImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 步骤二 图片绘制背景颜色 ---通过绘图对象
        Graphics graphics = bufferedImage.getGraphics();// 得到画图版
        // 绘制任何图形之前 都必须指定一个颜色
        graphics.setColor(getRandColor(200, 250));//设置画笔的颜色
        graphics.fillRect(0, 0, width, height);//填充一个矩形

        // 步骤三 绘制边框
        graphics.setColor(Color.WHITE); //设置边框颜色
        graphics.drawRect(0, 0, width - 1, height - 1);

        Graphics2D graphics2d = (Graphics2D) graphics;
        // 设置输出字体
        graphics2d.setFont(new Font("宋体", Font.BOLD, 18));

        Random random = new Random();// 生成随机数
        int index = random.nextInt(words.size());
        String word = words.get(index);// 随机获得一个成语
        System.out.println(word);

        // 定义x坐标
        int x = 10;
        for (int i = 0; i < word.length(); i++) {
            // 随机颜色
            graphics2d.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));//设置画笔的颜色
            // 旋转 -30 --- 30度
            int jiaodu = random.nextInt(60) - 30;//[-30,29]
            // 换算弧度
            double theta = jiaodu * Math.PI / 180;//换算弧度

            // 获得成语中的每一个汉字
            char c = word.charAt(i);

            // 将c 输出到图片
            graphics2d.rotate(theta, x, 20);//旋转指定的角度
            graphics2d.drawString(String.valueOf(c), x, 20);//在指定点的位置绘制文字
            graphics2d.rotate(-theta, x, 20);
            x += 30;//设置每个汉字之间的间隔
        }

        // 步骤五 绘制干扰线
        graphics.setColor(getRandColor(160, 200));//绘制画笔的颜色
        //设置两个点的坐标
        int x1;
        int x2;
        int y1;
        int y2;
        //绘制30条干扰线
        for (int i = 0; i < lineSize; i++) {
            x1 = random.nextInt(width);
            x2 = random.nextInt(12);
            y1 = random.nextInt(height);
            y2 = random.nextInt(12);
            graphics.drawLine(x1, y1, x1 + x2, x2 + y2);//绘制线
        }

        // 将上面图片输出到浏览器 ImageIO
        graphics.dispose();// 释放资源

        Map<String, Object> map = new HashMap<String, Object>();
        // 存放验证码字符串,比如:一心一意
        map.put("code", word);
        // 存放生成的验证码BufferedImage对象
        map.put("codePic", bufferedImage);
        //将图片写到response.getOutputStream()中
       // ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
        return map;//返回验证码
    }

    /**
     * 取其某一范围的color
     *
     * @param fc
     *            int 范围参数1
     * @param bc
     *            int 范围参数2
     * @return Color  RGB (255,255,255)
     */

    private static Color getRandColor(int fc, int bc) {

        // 取其随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc); //200 +random.nextInt(50) [200,249]
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);// 得到一个颜色
    }


}

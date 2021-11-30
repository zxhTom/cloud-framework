package com.github.zxhtom.poi.utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static BufferedImage getDefault(Integer width,Integer height,Integer imageType) {
        BufferedImage image = new BufferedImage(width, height, imageType);
        //获取图片的画布
        Graphics2D graphics = image.createGraphics();
        //使用 Graphics 类在图片上绘制线段、矩形、图片、文本，设置背景颜色等等操作
        graphics.drawOval(10,10,10,10);
        return image;
    }
}

package com.github.zxhtom.oss.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

// ImageProcessService.java
@Service
@Slf4j
public class ImageProcessService {

    /**
     * 生成缩略图
     */
    public BufferedImage generateThumbnail(BufferedImage originalImage, int maxWidth, int maxHeight) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // 计算缩放比例
        double scale = Math.min(
                (double) maxWidth / originalWidth,
                (double) maxHeight / originalHeight
        );

        int newWidth = (int) (originalWidth * scale);
        int newHeight = (int) (originalHeight * scale);

        // 创建缩放后的图片
        BufferedImage thumbnail = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = thumbnail.createGraphics();

        // 设置渲染参数
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制缩放后的图片
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();

        return thumbnail;
    }

    /**
     * 压缩图片
     */
    public BufferedImage compressImage(BufferedImage originalImage, float quality) {
        // 这里可以实现图片质量压缩
        // 实际项目中可以使用 ImageWriter 设置压缩质量
        return originalImage;
    }
}

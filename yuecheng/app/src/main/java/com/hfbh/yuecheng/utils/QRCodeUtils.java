package com.hfbh.yuecheng.utils;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.Hashtable;

/**
 * Author：Libin on 2018/5/23 16:14
 * Email：1993911441@qq.com
 * Describe：二维码生成
 */
public class QRCodeUtils {

    private static final int BLACK = 0xff000000;

    public static Bitmap createQRCode(String str, int widthAndHeight) {

        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, widthAndHeight, widthAndHeight);
        } catch (WriterException e) {

        }
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = BLACK;
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }
}

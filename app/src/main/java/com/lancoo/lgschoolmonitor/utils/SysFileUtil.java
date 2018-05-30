package com.lancoo.lgschoolmonitor.utils;

import java.io.File;
import java.text.DecimalFormat;

/**
 * File description.
 *
 * @author Hinata-Liu
 * @date 2018/5/28 17:25.
 */
public class SysFileUtil {
    /**
     * 获取文件夹大小
     *
     * @param file
     *            File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);

                } else {
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
        }
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param filepath
     * @return
     */
    public static void deleteFolderFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {// 处理目录
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFolderFile(files[i].getAbsolutePath());
                }
            }
            if (!file.isDirectory()) {// 如果是文件，删除
                file.delete();
            }
        } catch (Exception e) {
        }
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#0.0");
        String fileSizeString = "";
        String wrongSize = "0.0K";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}

package com.seven.seven.common.utils;

import android.content.Context;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Liuc on 2017-12-19.
 * 辅助创建文件夹和文件
 */

public class FileUtils {


    /**
     * 创建一个新的文件用来存储图片
     *
     * @param outputfile 输出路径
     * @return 是否创建成功
     */
    public static boolean creatFile(File outputfile) {
        boolean flag = true;
        try {
            outputfile.deleteOnExit();
            outputfile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 创建一个新的文件用来存储图片
     *
     * @param outputfile 输出路径
     * @return 是否创建成功
     */
    public static boolean creatDirs(File outputfile) {
        boolean flag = true;
        try {
            outputfile.deleteOnExit();
            flag = outputfile.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断assets文件夹下的文件是否存在
     *
     * @return false 不存在    true 存在
     */
    public static boolean isFileExists(Context context, String filename) {
        String[] names = new File(context.getCacheDir().getAbsolutePath() + File.separator + "error_1").list();
        LogUtils.e(Arrays.toString(names));
        if (names != null) {
            for (String name : names) {
                if (name.contains(filename.trim())) {
                    return true;
                }
            }
        }
        return false;
    }
}

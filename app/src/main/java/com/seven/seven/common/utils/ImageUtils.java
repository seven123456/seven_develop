package com.seven.seven.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Liuc on 2017-12-19.
 * 相机，相册，裁剪的工具类
 */

public class ImageUtils {

    /**
     * 打开相机
     */
    public static void cameraPic(Activity context, String filePath, int requestCode) {
        //创建一个file，用来存储拍照后的照片
        File outputfile = new File(filePath);
        //创建一个file，用来存储拍照后的照片
        FileUtils.creatFile(outputfile);
        Uri imageuri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageuri = FileProvider.getUriForFile(context,
                    context.getPackageName(), //可以是任意字符串
                    outputfile);
        } else {
            imageuri = Uri.fromFile(outputfile);
        }
        //启动相机程序
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivityForResult(intent, requestCode);
    }

    public static void albumPic(Activity context, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivityForResult(intent, requestCode);
    }


    /**
     * 拍照之后，启动裁剪
     *
     * @param camerapath 路径
     * @return
     */
    public static Uri cutForCamera(Activity context, String camerapath, String outputPath, String authority, int requestCode, int width, int heigh) {
        try {
            //设置裁剪之后的图片路径文件
            File cutfile = new File(outputPath);
            FileUtils.creatFile(cutfile);
            //初始化 uri
            Uri imageUri = null; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Intent intent = new Intent("com.android.camera.action.CROP");
            //拍照留下的图片
            File camerafile = new File(camerapath);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(context,
                        authority,
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            outputUri = Uri.fromFile(cutfile);
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", heigh);
            intent.putExtra("scale", true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            context.startActivityForResult(intent, requestCode);
            return outputUri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 图片裁剪
     *
     * @param uri
     * @return
     */
    public static Uri cutForPhoto(Activity context, Uri uri, String path, int requestCode, int width, int heigh) {
        try {
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //设置裁剪之后的图片路径文件
            File cutfile = new File(path + "/" + System.currentTimeMillis() + ".jpg"); //随便命名一个
            FileUtils.creatFile(cutfile);
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            outputUri = Uri.fromFile(cutfile);
            // crop为true是设置在开启的intent中设置显示的view可以剪裁
            intent.putExtra("crop", true);
            // aspectX,aspectY 是宽高的比例，这里设置正方形
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            //设置要裁剪的宽高
            intent.putExtra("outputX", width);
            intent.putExtra("outputY", heigh);
            intent.putExtra("scale", true);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
            context.startActivityForResult(intent, requestCode);
            return outputUri;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    /**
     * uri转换为bitmap
     * By:creeper
     */
    public static Bitmap decodeUriAsBitmap(Activity context, Uri uri) {
        Bitmap bitmap;
        try {
            //创建一个BitmapFactory.Options对象，且Options 只保存图片尺寸大小，不保存图片到内存
            BitmapFactory.Options options = new BitmapFactory.Options();

            /*
             * 如果该值设置为＞1，就会请求解析器对原图做二次抽样，即二次解析，返回一个较小的图片用来节省内存。
             * 二次抽样样本大小的像素尺寸,对应于一个解码位图的像素。举个例子，如果inSampleSize == 4，会返回一个是原图1/4高度和1/4宽度的图像，和1/16像素的数量。
             * 对任何值< = 1的值都用=1来赋值
             */
            options.inSampleSize = 4;

            //附加上图片的Config参数，解析器或根据当前的参数配置进行对应的解析，这也可以有效减少加载的内存
            options.inPreferredConfig = Bitmap.Config.RGB_565;

            //由此产生的位图将分配它的像素,这样他们可以被净化系统需要回收的内存
            options.inPurgeable = true;

            /*
             * inInputShareable 属性和 inPurgeable 有关，当 inPurgeable 属性设置为 false 的时候，inInputShareable 属性就可以忽略。
             * 但是如果这两个属性都设置为true的时候，源码的注释这样说：确定位图可以共享一个参考输入数据如果它必须深拷贝的话。
             * 不设置也是可以的。
             */
            options.inInputShareable = true;

            //根据options的一些选项设置解析输入流返回一个压缩后的Bitmap对象
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * bitmap质量压缩
     */
    public static Bitmap bmpCompress(Bitmap bitmap, int quality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] bytes = baos.toByteArray();
        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        LogUtils.d("压缩后图片的大小" + (bmp.getByteCount() / 1024 / 1024)
                + "M宽度为" + bmp.getWidth() + "高度为" + bmp.getHeight()
                + "bytes.length=  " + (bytes.length / 1024) + "KB"
                + "quality=" + quality);
        return bmp;
    }

    /**
     * 将bitmap转化为二进制字节流
     */
    public static byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * 二进制流转换bitmap
     */
    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            return BitmapFactory.decodeByteArray(temp, 0, temp.length);
        } else {
            return null;
        }
    }
    /*
    * 获取相册URI 不裁剪
    * */
    public static Uri getPhotoUri(Activity context, Uri uri, String path, int requestCode) {
        try {
            //直接裁剪
            Intent intent = new Intent("com.android.camera.action.CROP");
            //设置裁剪之后的图片路径文件
            File cutfile = new File(path + "/" + System.currentTimeMillis() + ".jpg"); //随便命名一个
            FileUtils.creatFile(cutfile);
            //初始化 uri
            Uri imageUri = uri; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            outputUri = Uri.fromFile(cutfile);
            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
            context.startActivityForResult(intent, requestCode);
            return outputUri;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    /*
    * 获取拍照URI  不裁剪
    * */
    public static Uri getCameraUri(Activity context, String camerapath, String outputPath, String authority, int requestCode) {
        try {
            //设置裁剪之后的图片路径文件
            File cutfile = new File(outputPath);
            FileUtils.creatFile(cutfile);
            //初始化 uri
            Uri imageUri = null; //返回来的 uri
            Uri outputUri = null; //真实的 uri
            Intent intent = new Intent("com.android.camera.action.CROP");
            //拍照留下的图片
            File camerafile = new File(camerapath);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(context,
                        authority,
                        camerafile);
            } else {
                imageUri = Uri.fromFile(camerafile);
            }
            outputUri = Uri.fromFile(cutfile);

            //如果图片过大，会导致oom，这里设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            //压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            context.startActivityForResult(intent, requestCode);
            return outputUri;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

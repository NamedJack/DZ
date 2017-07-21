package com.wongxd.partymanage.utils;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

/**
 * Created by wxd1 on 2017/7/4.
 */

public class OpenFileThing {
    enum FileType {
        img, text, audio, video, chm, apk, excel, word, pdf, ppt, none
    }

    public static void openAssignFolder(AppCompatActivity ctx, String path) {
        File file = new File(path);
        if (null == file || !file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri = Uri.fromFile(file);
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "file/*");
        try {
            ctx.startActivity(intent);
            ctx.startActivity(Intent.createChooser(intent, "选择浏览工具"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void openAssignFolder(AppCompatActivity ctx, String path, FileType fileType) {
        File file = new File(path);
        if (null == file || !file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri = Uri.fromFile(file);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        switch (fileType) {
            case img:
//                打开图片文件
                intent.setDataAndType(uri, "image/*");
                break;
            case pdf:
//                打开PDF文件
                intent.setDataAndType(uri, "application/pdf");
                break;
            case text:
//                打开文本文件
                intent.setDataAndType(uri, "text/plain");
                break;
            case audio:
//                打开音频文件
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("oneshot", 0);
                intent.putExtra("configchange", 0);
                intent.setDataAndType(uri, "audio/*");
                break;
            case video:
//                打开视频文件
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("oneshot", 0);
                intent.putExtra("configchange", 0);
                intent.setDataAndType(uri, "video/*");
                break;
            case chm:
//                打开CHM文件
                intent.setDataAndType(uri, "application/x-chm");
                break;
            case apk:
//                打开apk文件
                intent.setDataAndType(uri, "application/vnd.android.package-archive");
                break;

            case ppt:
//                打开PPT文件
                intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
                break;
            case excel:
//                打开Excel文件
                intent.setDataAndType(uri, "application/vnd.ms-excel");
                break;
            case word:
//                打开Word文件
                intent.setDataAndType(uri, "application/msword");
                break;
        }
        try {
            ctx.startActivity(intent);
            ctx.startActivity(Intent.createChooser(intent, "选择浏览工具"));
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Intent intent = new Intent(Intent.ACTION_VIEW);
     Uri uri = Uri.fromFile(file);
     intent.addCategory(Intent.CATEGORY_DEFAULT);
     打开图片文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "image/*");
     打开PDF文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "application/pdf");
     打开文本文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "text/plain");
     打开音频文件
     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     intent.putExtra("oneshot", 0);
     intent.putExtra("configchange", 0);
     intent.setDataAndType(uri, "audio/*");
     打开视频文件
     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     intent.putExtra("oneshot", 0);
     intent.putExtra("configchange", 0);
     intent.setDataAndType(uri, "video/*");
     打开CHM文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "application/x-chm");
     打开apk文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "application/vnd.android.package-archive");
     打开PPT文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
     打开Excel文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "application/vnd.ms-excel");
     打开Word文件
     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     intent.setDataAndType(uri, "application/msword");

     */

}

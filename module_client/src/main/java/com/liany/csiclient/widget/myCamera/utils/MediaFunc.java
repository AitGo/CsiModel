/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liany.csiclient.widget.myCamera.utils;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @创建者 ly
 * @创建时间 2019/12/30
 * @描述
 * @更新者 $
 * @更新时间 $
 * @更新描述
 */
public class MediaFunc {

    private static String TAG = "MediaFunc";
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    public static final int MEDIA_TYPE_YUV = 3;
    public static final String SAVE_PATH = "cameraview";

    private static Uri mCurrentUri = null;

    public static File getOutputMediaFile(int type, String tag) {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "cameraview" + File.separator + "photo/");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(TAG, "failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath()
                    + File.separator + "Finger_" + tag + "_" + timeStamp + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath()
                    + File.separator + "VID_" + tag + "_" + timeStamp + ".mp4");
        } else if (type == MEDIA_TYPE_YUV) {
            mediaFile = new File(mediaStorageDir.getPath()
                    + File.separator + "IMG_" + tag + "_" + timeStamp + ".yuv");
        }else {
            return null;
        }
        return mediaFile;
    }

    public static File getStorageDir() {
        return new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), SAVE_PATH);
    }

    public static Bitmap getThumb(Context context) {
        String selection = MediaStore.Images.Media.DATA + " like ?";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getPath() + "/" + SAVE_PATH;
        String[] selectionArgs = {path + "%"};
        Uri originalUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(originalUri, null, selection, selectionArgs,
                MediaStore.Images.Media.DATE_TAKEN + " desc");
        Bitmap bitmap = null;
        if (cursor != null && cursor.moveToFirst()) {
            long thumbNailsId = cursor.getLong(cursor.getColumnIndex("_ID"));
            //generate uri
            mCurrentUri = Uri.parse("content://media/external/images/media/");
            mCurrentUri = ContentUris.withAppendedId(mCurrentUri, thumbNailsId);
            bitmap = MediaStore.Images.Thumbnails.getThumbnail(cr,
                    thumbNailsId, MediaStore.Images.Thumbnails.MICRO_KIND, null);
        }
        cursor.close();
        return bitmap;
    }

    public static Bitmap getVideoThumb(Context context) {
        String selection = MediaStore.Video.Media.DATA + " like ?";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getPath() + "/" + SAVE_PATH;
        String[] selectionArgs = {path + "%"};
        Uri originalUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = context.getContentResolver();
        Cursor cursor = cr.query(originalUri, null, selection, selectionArgs,
                MediaStore.Video.Media.DATE_TAKEN + " desc");
        Bitmap bitmap = null;
        if (cursor != null && cursor.moveToFirst()) {
            long thumbNailsId = cursor.getLong(cursor.getColumnIndex("_ID"));
            //generate uri
            mCurrentUri = Uri.parse("content://media/external/video/media/");
            mCurrentUri = ContentUris.withAppendedId(mCurrentUri, thumbNailsId);
            bitmap = MediaStore.Video.Thumbnails.getThumbnail(cr,
                    thumbNailsId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
        }
        cursor.close();
        return bitmap;
    }

    public static Cursor getAllImage(Context context) {
        String selection = MediaStore.Images.Media.DATA + "" + " like ?";
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                .getPath() + "/" + SAVE_PATH;
        String[] selectionArgs = {path + "%"};
        Cursor c = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, selection, selectionArgs,
                MediaStore.Images.Media.DATE_TAKEN + " desc");
        if (c != null && c.moveToFirst()) {
            return c;
        } else {
            return null;
        }
    }

    public static void setCurrentUri(Uri uri) {
        mCurrentUri = uri;
    }

    public static void goToGallery(Context context) {
        if (mCurrentUri == null) {
            Log.e(TAG, "uri is null");
            return;
        }
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN).setClassName(
                    "com.android.gallery3d", "com.android.gallery3d.app.GalleryActivity");
            intent.setAction(Intent.ACTION_VIEW);
            //intent.setDataAndType(uri,"image/*");
            intent.setData(mCurrentUri);
            context.startActivity(intent);
        } catch (Exception e) {
            Intent intent = new Intent(Intent.ACTION_VIEW, mCurrentUri);
            ComponentName componentName = intent.resolveActivity(context.getPackageManager());
            if (componentName == null) {
                Toast.makeText(context, "Can not found app to open image/video", Toast.LENGTH_LONG).show();
                return;
            }
            context.startActivity(intent);
        }
    }

}

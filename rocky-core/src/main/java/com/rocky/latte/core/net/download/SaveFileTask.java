package com.rocky.latte.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.rocky.latte.core.app.Latte;
import com.rocky.latte.core.net.callback.IRequest;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.core.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/1/18
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private ResponseCallback CALLBACK;
    private IRequest IREQUEST;

    public SaveFileTask(ResponseCallback CALLBACK, IRequest IREQUEST) {
        this.CALLBACK = CALLBACK;
        this.IREQUEST = IREQUEST;
    }

    @Override
    protected File doInBackground(Object... params) {
        ResponseBody body = (ResponseBody) params[0];
        //下载文件夹路径
        String downloadDir = (String) params[1];
        //文件名
        String extension = (String) params[2];
        //文件后缀名
        String name = (String) params[3];
        final InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.isEmpty())
            downloadDir = "download_file";
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null || name.equals("")) {
            return FileUtil.writeToDisk(is, downloadDir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (CALLBACK != null)
            CALLBACK.onSuccess(file.getPath());

        if (IREQUEST != null)
            IREQUEST.onRequestFinish();

        autoInstallApk(file);

    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}

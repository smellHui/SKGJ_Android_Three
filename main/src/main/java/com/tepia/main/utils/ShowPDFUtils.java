package com.tepia.main.utils;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.view.dialog.loading.SimpleLoadDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by      android studio
 *
 * @author :       ly
 * Date            :       2018-09-26
 * Time            :       下午3:36
 * Version         :       1.0
 * location        :       武汉研发中心
 * 功能描述         :       在线加载pdf类
 **/
public class ShowPDFUtils {
    /**
     * 请在子线程中使用
     */

    /**
     * 获取文件流
     *
     * @param pdfUrl
     * @return
     */
    public static void showPDF(PDFView pdfView, SimpleLoadDialog simpleLoadDialog, String pdfUrl) {
        /**
         * 请在子线程中使用
         */
        try {
            URL url = new URL(pdfUrl);
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            //实现连接
            connection.connect();

            System.out.println("connection.getResponseCode()=" + connection.getResponseCode());
            if (connection.getResponseCode() == 200) {

                InputStream inStream = connection.getInputStream();
                loadpdf(pdfView,simpleLoadDialog,inStream);

                //这里给过去就行了
            }else{
                if (simpleLoadDialog != null) {
                    simpleLoadDialog.dismiss();
                }
                LogUtil.e("pdf文件加载失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (simpleLoadDialog != null) {
                simpleLoadDialog.dismiss();
            }
        }



    }

    /**
     * 加载服务器上的pdf转化成的文件流
     *
     * @param stream
     */
    public static void loadpdf(PDFView pdfView, SimpleLoadDialog simpleLoadDialog,InputStream stream) {
        pdfView.fromStream(stream)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .defaultPage(0)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                    }
                })
                .onError(new OnErrorListener() {
                    @Override
                    public void onError(Throwable t) {
                        if (simpleLoadDialog != null) {
                            simpleLoadDialog.dismiss();
                        }
                        LogUtil.e("pdf文件加载失败");
                    }
                })
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();

    }
}

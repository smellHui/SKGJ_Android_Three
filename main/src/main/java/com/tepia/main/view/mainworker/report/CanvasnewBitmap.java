package com.tepia.main.view.mainworker.report;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;

public class CanvasnewBitmap {

		public static Bitmap doodle(Bitmap src, Bitmap watermark)  
	    {  
	        // ���ⴴ��һ��ͼƬ  
	        Bitmap newb = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Config.ARGB_8888);
	        Canvas canvas = new Canvas(newb);  
	        canvas.drawBitmap(src, 0, 0, null);
	        canvas.drawBitmap(watermark, (src.getWidth() - watermark.getWidth()) / 2, (src.getHeight() - watermark.getHeight()) / 2, null);
	        canvas.save(Canvas.ALL_SAVE_FLAG);
	        canvas.restore();  

	        return newb;  
	    }  

}

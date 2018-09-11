package com.tepia.base.utils.imageload;

import android.graphics.Bitmap;

/**
 * Created by JohnsonFan on 2018/3/7.
 */

public interface BitmapCallBack {

	void onBitmapLoaded(Bitmap bitmap);

	void onBitmapFailed(Exception e);

	class EmptyCallback implements BitmapCallBack {


		@Override
		public void onBitmapLoaded(Bitmap bitmap) {

		}

		@Override
		public void onBitmapFailed(Exception e) {

		}
	}
}

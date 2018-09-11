package com.tepia.base.utils.imageload;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.tepia.base.utils.Utils;

/**
 * Created by BLiYing on 2018/6/5.
 */

public class GlideLoader implements ILoaderStrategy{
    private volatile static RequestManager requestManager;

    private static RequestManager getGlide(){
        if (requestManager == null) {
            synchronized (GlideLoader.class){
                if (requestManager == null) {
                    requestManager = Glide.with(Utils.getContext());
                }
            }
        }
        return requestManager;

    }

    @Override
    public void loadImage(LoaderOptions options) {
        if (options.url != null) {

        }
    }

    @Override
    public void clearMemoryCache() {

    }

    @Override
    public void clearDiskCache() {

    }
}

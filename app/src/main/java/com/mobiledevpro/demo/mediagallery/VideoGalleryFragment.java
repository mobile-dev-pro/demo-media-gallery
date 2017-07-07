package com.mobiledevpro.demo.mediagallery;

import com.mobiledevpro.mediagallery.view.BaseVideoGalleryFragment;

/**
 * Fragment for Video Gallery
 * <p>
 * Created by Dmitriy V. Chernysh on 07.07.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://www.facebook.com/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

public class VideoGalleryFragment extends BaseVideoGalleryFragment {

    public static VideoGalleryFragment newInstance() {
        return new VideoGalleryFragment();
    }

    @Override
    protected int getAppBarTitle() {
        return R.string.app_title_select_videos;
    }

    @Override
    protected int getAppBarColor() {
        return R.color.colorPrimary;
    }

    @Override
    protected int getHomeAsUpIndicatorIcon() {
        return R.drawable.ic_close_24dp;
    }
}

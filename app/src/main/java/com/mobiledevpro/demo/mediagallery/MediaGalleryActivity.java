package com.mobiledevpro.demo.mediagallery;

import android.support.v4.app.FragmentTransaction;

import com.mobiledevpro.mediagallery.view.BaseMediaGalleryActivity;

/**
 * Media Gallery
 * <p>
 * Created by Dmitriy V. Chernysh on 07.07.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://www.facebook.com/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

public class MediaGalleryActivity extends BaseMediaGalleryActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gallery;
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar;
    }

    @Override
    protected void initPhotoGalleyFragment() {
        //not implemented yet
    }

    @Override
    protected void initVideoGalleryFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(
                R.id.fragment_container,
                VideoGalleryFragment.newInstance()
        ).commit();
    }

    @Override
    protected int getStartEnterAnimation() {
        return R.anim.anim_activity_start_slide_up;
    }

    @Override
    protected int getStartExitAnimation() {
        return android.R.anim.fade_in;
    }

    @Override
    protected int getFinishEnterAnimation() {
        return R.anim.anim_activity_finish_slide_down;
    }

    @Override
    protected int getFinishExitAnimation() {
        return android.R.anim.fade_out;
    }
}

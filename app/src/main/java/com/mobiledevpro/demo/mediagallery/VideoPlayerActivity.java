package com.mobiledevpro.demo.mediagallery;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatDelegate;
import android.view.TextureView;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.cdvdev.player.BaseVideoPlayerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Video player
 * <p>
 * Created by Dmitriy V. Chernysh on 07.07.17.
 * dmitriy.chernysh@gmail.com
 * <p>
 * https://www.facebook.com/mobiledevpro/
 * <p>
 * #MobileDevPro
 */

public class VideoPlayerActivity extends BaseVideoPlayerActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @BindView(R.id.video_view)
    TextureView mVideoView;

    @BindView(R.id.seek_bar)
    SeekBar mSeekBar;

    @BindView(R.id.btn_action_play_stop_seekbar)
    ImageButton mBtnPlayPause;

    @BindView(R.id.tv_seek_bar_timer)
    TextView mSeekBarTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for TextureView correct working
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        overridePendingTransition(
                R.anim.anim_activity_start_slide_up,
                android.R.anim.fade_out
        );

        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(
                android.R.anim.fade_in,
                R.anim.anim_activity_finish_slide_down
        );
    }

    @Override
    public void fadeIn(final int duration) {
        if (mVideoView == null) return;
        mVideoView.post(new Runnable() {
            @Override
            public void run() {
                mVideoView.setAlpha(0f);
                mVideoView.animate().alpha(1f).setDuration(duration).start();
            }
        });
    }

    @Override
    public void fadeOut(final int duration) {
        if (mVideoView == null) return;
        mVideoView.post(new Runnable() {
            @Override
            public void run() {
                mVideoView.setAlpha(1f);
                mVideoView.animate().alpha(0f).setDuration(duration).start();
            }
        });
    }

    @Override
    public void setSeekBarProgress(int timePosition) {
        if (mSeekBar == null) return;
        mSeekBar.setProgress(timePosition);
    }

    @Override
    public void setSeekBarMax(int maxValue) {
        if (mSeekBar == null) return;
        mSeekBar.setMax(maxValue);
    }

    @Override
    public void setPlayPauseButtonState(boolean isPlaying) {
        if (mBtnPlayPause == null) return;
        mBtnPlayPause.setImageDrawable(
                getVectorDrawable(
                        isPlaying
                                ? R.drawable.ic_pause_white_48dp
                                : R.drawable.ic_play_white_48dp
                )
        );
    }

    @Override
    public void setTimerValue(String value) {
        if (mSeekBarTimer == null) return;
        mSeekBarTimer.setText(value);
    }

    @Override
    public void setVideoViewSize(int width, int height) {
        if (mVideoView == null) return;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        params.width = width;
        params.height = height;
        mVideoView.setLayoutParams(params);

    }

    @OnClick(R.id.btn_action_play_stop_seekbar)
    void onPlayPause(ImageButton imgBtn) {
        mPresenter.onPlayPausePressed();
    }

    private void initView() {
        if (mVideoView != null && mPresenter != null) {
            mVideoView.post(new Runnable() {
                @Override
                public void run() {
                    mVideoView.setSurfaceTextureListener(mPresenter);
                    //not called onSurfaceTextureAvailable() automatically every time. Need to call it manually.
                    if (mVideoView.isAvailable()) {
                        mPresenter.onSurfaceTextureAvailable(mVideoView.getSurfaceTexture(), mVideoView.getWidth(), mVideoView.getHeight());
                    }
                }
            });
        }
        if (mSeekBar != null && mPresenter != null) {
            mSeekBar.setOnSeekBarChangeListener(mPresenter);
        }
    }

    /**
     * Get VectorDrawable compatible with API < 23
     *
     * @param resId Drawable res id
     * @return Drawable
     */
    private Drawable getVectorDrawable(@DrawableRes int resId) {
        if (resId == 0) return null;

        VectorDrawableCompat d = VectorDrawableCompat.create(
                this.getResources(),
                resId,
                null);
        if (d == null) return null;

        return DrawableCompat.wrap(d);
    }
}

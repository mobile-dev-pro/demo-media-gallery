package com.mobiledevpro.demo.mediagallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_MEDIA_GALLERY = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_MEDIA_GALLERY:
                if (resultCode != Activity.RESULT_OK || data == null) return;
                Bundle extras = data.getExtras();
                //Media Gallery returns array list of file paths
                ArrayList<String> fileUrls = extras.getStringArrayList(MediaGalleryActivity.EXTRA_KEY_SELECTED_FILE_URLS);
                Toast.makeText(
                        this,
                        "You have selected " +
                                (fileUrls != null && !fileUrls.isEmpty() ? fileUrls.size() : 0) +
                                " videos",
                        Toast.LENGTH_SHORT
                ).show();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initView() {
        TextView tvContactUs = (TextView) findViewById(R.id.tv_contact_us);
        if (tvContactUs != null) {
            tvContactUs.setMovementMethod(LinkMovementMethod.getInstance());
        }

        Button btnVideoGallery = (Button) findViewById(R.id.btn_video_lib);
        if (btnVideoGallery != null) {
            btnVideoGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showVideoGallery();
                }
            });
        }
    }

    private void showVideoGallery() {
        Intent intent = new Intent(this, MediaGalleryActivity.class);
        intent.putExtra(MediaGalleryActivity.EXTRA_KEY_LAUNCH_FRAGMENT, MediaGalleryActivity.EXTRA_KEY_GALLERY_VIDEO);
        startActivityForResult(intent, REQUEST_CODE_MEDIA_GALLERY);
    }
}

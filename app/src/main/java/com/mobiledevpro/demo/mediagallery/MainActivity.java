package com.mobiledevpro.demo.mediagallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView tvContactUs = (TextView) findViewById(R.id.tv_contact_us);
        if (tvContactUs != null) {
            tvContactUs.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}

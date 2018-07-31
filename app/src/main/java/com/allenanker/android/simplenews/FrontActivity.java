package com.allenanker.android.simplenews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class FrontActivity extends AppCompatActivity {
    private TextView textView;
    private int tm = 5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);
        textView = findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrontActivity.this, LoginPageActivity.class);
                startActivity(intent);
                FrontActivity.this.finish();
            }
        });
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        tm--;
                        textView.setText("跳过" + tm);
                        if (tm < 1) {
                            timer.cancel();
                            Intent intent = new Intent(FrontActivity.this, LoginPageActivity.class);
                            startActivity(intent);
                            FrontActivity.this.finish();
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);

    }

}

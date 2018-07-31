package com.allenanker.android.simplenews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Front extends Activity {
    private TextView textView;
    private int tm=5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);
        textView=findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Front.this, TestShow8.class);
                startActivity(intent);
            }
        });
        final Timer timer=new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        tm--;
                        textView.setText("跳过"+tm);
                        if(tm < 1){
                            timer.cancel();
                            Intent intent=new Intent(Front.this, TestShow8.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        };timer.schedule(task, 1000, 1000);

    }

}

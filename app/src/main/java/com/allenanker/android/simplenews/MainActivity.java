package com.allenanker.android.simplenews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
//    private Button mShowNewsButton;
//    private Button mLoginButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mShowNewsButton = findViewById(R.id.show_news_button);
//        mShowNewsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, NewsListActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        mLoginButton = findViewById(R.id.login_button);
//        mLoginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, TestShow8.class);
//                startActivity(intent);
//            }
//        });
//    }

    private RelativeLayout welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        welcome = findViewById(R.id.welcome_page);
        showAnimation();
        final Intent intent = new Intent(MainActivity.this, Front.class);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run(){
                MainActivity.this.finish();
                startActivity(intent);
            }
        };
        timer.schedule(task,3500);
    }

    private void showAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);


        RotateAnimation rotateAnimation = new RotateAnimation(0,360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(2000);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);

        welcome.startAnimation(animationSet);
    }
}
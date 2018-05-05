package testing.gps_service;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import com.eftimoff.androipathview.PathView;


public class IntroActivity extends AppCompatActivity {

    ImageView pic;
    Animation fromtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().hide();


        final PathView pathView = (PathView) findViewById(R.id.pathView);
        pathView.getPathAnimator()
                .delay(1000)
                .duration(10000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();

        pathView.useNaturalColors();*/
       // pathView.setFillAfter(true);
        final PathView pathView = (PathView) findViewById(R.id.pathView);
        pathView.getPathAnimator()
                .delay(1000)
                .duration(7000)
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
        pathView.useNaturalColors();
        pic = (ImageView) findViewById(R.id.pic);
        pic.bringToFront();
        fromtop = AnimationUtils.loadAnimation(this,R.anim.fromtop);
        pic.setAnimation(fromtop);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, 6100);
    }
}

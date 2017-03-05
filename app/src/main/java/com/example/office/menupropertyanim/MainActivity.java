package com.example.office.menupropertyanim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView[] imageViews=new ImageView[6];
    private boolean isOpen=false;
    private List<Animator> listAnimClose=new ArrayList<>();//关闭动画集合
    private List<Animator> listAnimOpen=new ArrayList<>();//打开动画集合

    double radian=2*3.14/360;
    double angle=22.5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageViews[0]= (ImageView) findViewById(R.id.image);
        imageViews[1]= (ImageView) findViewById(R.id.image1);
        imageViews[2]= (ImageView) findViewById(R.id.image2);
        imageViews[3]= (ImageView) findViewById(R.id.image3);
        imageViews[4]= (ImageView) findViewById(R.id.image4);
        imageViews[5]= (ImageView) findViewById(R.id.image5);

        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image:
                if (!isOpen){
                    openMenu();
                }else {
                    closeMenu();
                }
                break;
            case R.id.image1:
                Toast.makeText(MainActivity.this,"location",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image2:
                Toast.makeText(MainActivity.this,"sleep",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image3:
                Toast.makeText(MainActivity.this,"music",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image4:
                Toast.makeText(MainActivity.this,"take a photo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image5:
                Toast.makeText(MainActivity.this,"me",Toast.LENGTH_SHORT).show();
                break;

        }

    }

    private void closeMenu() {
        isOpen=false;
        AnimatorSet animset=new AnimatorSet();
        for (int i = 1; i <imageViews.length ; i++) {
            AnimatorSet animsetItem=new AnimatorSet();
            //Math.cos()，参数为角度的弧度制；如：（2*3.14）/360*(当前角度)
            ObjectAnimator objectAnimX=ObjectAnimator.ofFloat(imageViews[i],"translationX",(float) (400*Math.cos(radian*angle*(i-1))),0f);
            ObjectAnimator objectAnimY=ObjectAnimator.ofFloat(imageViews[i],"translationY",(float) (400*Math.sin(radian*angle*(i-1))),0f);
            ObjectAnimator objectAnimR=ObjectAnimator.ofFloat(imageViews[i],"rotation",0f,360f);
            animsetItem.setDuration(400);
            animsetItem.setInterpolator(new AccelerateInterpolator());
            animsetItem.playTogether(objectAnimX,objectAnimY,objectAnimR);
            listAnimClose.add(animsetItem);
        }
        animset.playTogether(listAnimClose);
        animset.setDuration(400);
        animset.start();
    }
    private void openMenu(){
        isOpen=true;
        AnimatorSet animset=new AnimatorSet();
        for (int i = 1; i <imageViews.length ; i++) {
            AnimatorSet animsetItem=new AnimatorSet();
            ObjectAnimator objectAnimX=ObjectAnimator.ofFloat(imageViews[i],"translationX",0f,(float) (400*Math.cos(radian*angle*(i-1))));
            ObjectAnimator objectAnimY=ObjectAnimator.ofFloat(imageViews[i],"translationY",0f,(float) (400*Math.sin(radian*angle*(i-1))));
            ObjectAnimator objectAnimR=ObjectAnimator.ofFloat(imageViews[i],"rotation",0f,360f);
            animsetItem.setDuration(800);
            animsetItem.setInterpolator(new BounceInterpolator());
            animsetItem.playTogether(objectAnimX,objectAnimY,objectAnimR);
            listAnimOpen.add(animsetItem);
        }
        animset.playTogether(listAnimOpen);
        animset.setDuration(800);
        animset.start();
    }
}

## 卫星菜单

使用ObjectAnimator实现卫星菜单

打开菜单：

    private boolean isOpen=false;
    private List<Animator> listAnimClose=new ArrayList<>();//关闭动画集合
    private List<Animator> listAnimOpen=new ArrayList<>();//打开动画集合
    double radian=2*Math.PI/360;
    double angle=22.5;

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
    
关闭菜单：

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



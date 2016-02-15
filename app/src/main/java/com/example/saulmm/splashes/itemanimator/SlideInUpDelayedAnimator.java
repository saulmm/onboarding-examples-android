package com.example.saulmm.splashes.itemanimator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;


public class SlideInUpDelayedAnimator extends BaseItemAnimator {

    private final int offsetDelay = 200;
    private final Interpolator mInterpolator;

    public SlideInUpDelayedAnimator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    @Override
    protected void preAnimateAdd(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationY(holder.itemView, holder.itemView.getHeight());
        ViewCompat.setAlpha(holder.itemView, 0);
    }
    
    @Override
    protected ViewPropertyAnimatorCompat onAnimatedAdd(RecyclerView.ViewHolder holder) {
        return ViewCompat.animate(holder.itemView)
                .translationY(0)
                .setInterpolator(mInterpolator)
                .setStartDelay(offsetDelay*holder.getLayoutPosition());
    }
}
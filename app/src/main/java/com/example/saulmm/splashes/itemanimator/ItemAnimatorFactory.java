package com.example.saulmm.splashes.itemanimator;

import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by renaud on 14/12/15.
 */
public class ItemAnimatorFactory {

    static public RecyclerView.ItemAnimator slidein() {
        SlideInUpDelayedAnimator animator = new SlideInUpDelayedAnimator(new DecelerateInterpolator(3f));
        animator.setAddDuration(700);
        return animator;
    }
}

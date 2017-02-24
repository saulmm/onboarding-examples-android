/*
 * Copyright (C) 2017
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.saulmm.splashes.itemanimator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Interpolator;


@SuppressWarnings("FieldCanBeLocal")
class SlideInUpDelayedAnimator extends BaseItemAnimator {
    private final int offsetDelay = 200;
    private final Interpolator mInterpolator;

    SlideInUpDelayedAnimator(Interpolator interpolator) {
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
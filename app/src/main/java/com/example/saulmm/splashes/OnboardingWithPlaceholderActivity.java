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
package com.example.saulmm.splashes;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saulmm.splashes.databinding.ActivityOnboardingPlaceholderBinding;
import com.example.saulmm.splashes.itemanimator.ItemAnimatorFactory;

import java.util.ArrayList;
import java.util.List;

public class OnboardingWithPlaceholderActivity extends AppCompatActivity {
    private final RecyclerAdapter mAdapter = new RecyclerAdapter();
    private int mContentViewHeight;

    private ActivityOnboardingPlaceholderBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fake a long startup time
        new Handler().postDelayed(this::onFakeCreate, 500);
    }

    private void onFakeCreate() {
        setTheme(R.style.AppTheme);

        mBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_onboarding_placeholder);

        ViewCompat.animate(mBinding.textTitle)
            .alpha(1)
            .start();

        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recycler.setItemAnimator(ItemAnimatorFactory.slidein());
        mBinding.recycler.setAdapter(mAdapter);

        mBinding.toolbar.post(() -> {
            mContentViewHeight = mBinding.toolbar.getHeight();

            startCollapseToolbarAnimation(() -> {
                // Fire item animator
                mAdapter.addAll(ModelItem.getFakeItems());

                // Animate fab
                ViewCompat.animate(mBinding.fab)
                    .setStartDelay(600)
                    .setDuration(400)
                    .scaleY(1)
                    .scaleX(1)
                    .start();
            });
        });
    }

    private void startCollapseToolbarAnimation(Runnable onCollapseEnd) {
        final ValueAnimator valueHeightAnimator = ValueAnimator
            .ofInt(mContentViewHeight, getToolbarHeight(this));

        valueHeightAnimator.addUpdateListener(animation -> {
            ViewGroup.LayoutParams lp = mBinding.toolbar.getLayoutParams();
            lp.height = (Integer) animation.getAnimatedValue();
            mBinding.toolbar.setLayoutParams(lp);
        });

        valueHeightAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                onCollapseEnd.run();
            }
        });

        valueHeightAnimator.start();
    }

    private static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
        private final ArrayList<ModelItem> mItems = new ArrayList<>();

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
            return new RecyclerViewHolder(v);
        }

        void addAll(List<ModelItem> items) {
            int pos = getItemCount();
            mItems.addAll(items);
            notifyItemRangeInserted(pos, mItems.size());
        }

        @Override
        public void onBindViewHolder(RecyclerViewHolder holder, int position) {
            holder.bind(mItems.get(position));
        }

        @Override
        public int getItemCount()    {
            return mItems.size();
        }


        private static class RecyclerViewHolder extends RecyclerView.ViewHolder {
            private TextView mTitleTextView;
            private ImageView mImageView;

            RecyclerViewHolder(View itemView) {
                super(itemView);
                mTitleTextView = (TextView) itemView.findViewById(R.id.text_title);
                mImageView = (ImageView) itemView.findViewById(R.id.img_sampleimage);
            }

            void bind(ModelItem modelItem) {
                mImageView.setImageBitmap(BitmapFactory.decodeResource(
                    itemView.getResources(), modelItem.getImgId()));
                mTitleTextView.setText(modelItem.getAuthor());
            }
        }
    }

    private static int getToolbarHeight(Context context) {
        final TypedValue tv = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
        return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
    }
}
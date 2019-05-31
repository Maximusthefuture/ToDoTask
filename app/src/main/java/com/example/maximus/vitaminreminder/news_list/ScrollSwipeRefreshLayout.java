package com.example.maximus.vitaminreminder.news_list;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ScrollSwipeRefreshLayout extends SwipeRefreshLayout {

    private View mScrollView;

    public ScrollSwipeRefreshLayout(@NonNull Context context) {
        super(context);
    }

    public ScrollSwipeRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if (mScrollView != null) {
            return ViewCompat.canScrollVertically(mScrollView, -1);
        }
        return super.canChildScrollUp();
    }

    public void setScrollUp(View view) {
        mScrollView = view;
    }
}

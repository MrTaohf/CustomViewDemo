package com.taohf.customviewdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hongfei.tao on 2017/4/19 19:08.
 */
public class SimpleLayout extends ViewGroup {

    public SimpleLayout(Context context) {
        super(context);
    }

    public SimpleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() > 0) {
            View childView = getChildAt(0);
            childView.layout(100, 100, childView.getMeasuredWidth() + 100, childView.getMeasuredHeight() + 100);
        }
    }
}

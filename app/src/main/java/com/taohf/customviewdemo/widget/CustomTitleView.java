package com.taohf.customviewdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.taohf.customviewdemo.R;

/**
 * 竖直方向的TextView
 * Created by hongfei.tao on 2017/4/18 18:45.
 */
public class CustomTitleView extends View {

    private String mTitleText;
    private int mTitleColor;
    private int mTitleSize;
    private Paint mPaint;
    private Rect mBound;

    public CustomTitleView(Context context) {
        this(context, null);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 获取自定义的属性值
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleView);
        int indexCount = a.getIndexCount();
        if (indexCount > 0)
            for (int i = 0; i < indexCount; i++) {
                int attr = a.getIndex(i);

                switch (attr) {
                    case R.styleable.CustomTitleView_titleText:
                        mTitleText = a.getString(attr);
                        break;
                    case R.styleable.CustomTitleView_titleTextColor:
                        mTitleColor = a.getColor(attr, Color.BLACK);
                        break;
                    case R.styleable.CustomTitleView_titleTextSize:
                        mTitleSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                        break;
                }
            }

        a.recycle();

        // 获取绘制文本的宽和高
        mPaint = new Paint();
        mPaint.setTextSize(mTitleSize);
//        mPaint.setColor(mTitleColor);

        mBound = new Rect();
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        canvas.translate(150, 0);
//        canvas.rotate(90);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(150, 0, 200 + 150, measuredHeight, mPaint);

        mPaint.setColor(mTitleColor);
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}

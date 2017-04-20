package com.taohf.customviewdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by hongfei.tao on 2017/4/19 19:32.
 */
public class CustomView extends View {

    private Paint mPaint;
    private Rect mBounds;
    private float mMargin;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBounds = new Rect();
        mPaint = new Paint();
        mMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5f);
        canvas.drawLine(0, 0, getWidth(), 0, mPaint);
        canvas.drawLine(0, 0, 0, getHeight(), mPaint);
        canvas.drawLine(0, getHeight(), getWidth(), getHeight(), mPaint);
        canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), mPaint);

        String drawText = "Hello View";
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 24, getResources().getDisplayMetrics()));
        mPaint.getTextBounds(drawText, 0, drawText.length(), mBounds);
        mPaint.setColor(Color.RED);
        canvas.drawText(drawText, mMargin, mMargin + mBounds.height(), mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.translate(0, getHeight());
        canvas.rotate(-90);
        canvas.drawText(drawText, mMargin, mMargin + mBounds.height(), mPaint);

        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.BLACK);
        canvas.translate(getWidth(), 0);
        canvas.rotate(90);
        canvas.drawText(drawText, mMargin, mMargin + mBounds.height(), mPaint);

        // restore()的调用次数最好和save()成对出现，否则会发生错误
        canvas.restore();
        canvas.save();
        canvas.translate(getWidth(), getHeight());
        canvas.rotate(180);
        canvas.drawText(drawText, mMargin, mMargin + mBounds.height(), mPaint);

    }
}

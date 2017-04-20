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

        // canvas坐标系 和 绘图(Matrix)坐标系 
        // canvas坐标系：在view的左上角
        // matrix绘图坐标系： 坐标系的原点初始状态是和canvas坐标原点相同的，但后续经过translate,rotate,scale等操作后，坐标系原点会发生变化
        // matrix坐标系经过多次的translate,rotate,scale等操作的时候，需要合理的使用canvas的save()方法和restore()方法，使得save()和
        // restore()达到一个平衡状态，即restore的使用次数必须<=save的次数，否则会出现错误绘制的情况。
        
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

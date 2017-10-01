package com.example.cliderecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/10/1 0001.
 */

public class HeartShapeView extends View {
    Paint mPaint;
    Path mPath;
    int heartColor;
    int width, height;
    public HeartShapeView(Context context) {
        super(context);
    }

    public HeartShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeartView);
        heartColor = ta.getColor(R.styleable.HeartView_heartColor, Color.BLUE);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(heartColor);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        mPath.moveTo(width / 2, height /4);
        mPath.cubicTo(width, 0, width, 9 * height / 10, width / 2, height);
        //mPath.rCubicTo(0, 3 / 4 * height, 0, 0, width / 2, height / 4);
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
        mPath.moveTo(width / 2, height / 4);
        mPath.cubicTo(0, 0, 0, 9 * height / 10, width / 2, height);
        canvas.drawPath(mPath, mPaint);
    }
}

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
 * Created by Administrator on 2017/10/3 0003.
 */

public class ForkShapeView extends View {

    Paint mPaint;
    Path mPath;
    int forkColor;
    int canvasColor;
    int width, height;
    public ForkShapeView(Context context) {
        super(context);
    }

    public ForkShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeartView);
        forkColor = ta.getColor(R.styleable.HeartView_forkColor, Color.BLACK);
        canvasColor = ta.getColor(R.styleable.HeartView_fCanvasColor, Color.WHITE);
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        mPaint.setColor(canvasColor);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(width / 2, height / 2, width / 2, mPaint);
        mPaint.setColor(forkColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        mPath.moveTo(width / 3, height / 3);
        mPath.lineTo(2 * width / 3, 2 * height / 3);
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
        mPath.moveTo(width / 3, 2 * height /3);
        mPath.lineTo(2 * width / 3, height / 3);
        canvas.drawPath(mPath, mPaint);
    }
}

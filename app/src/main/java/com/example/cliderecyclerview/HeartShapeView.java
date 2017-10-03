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
    int heartColor, canvasColor;
    int width, height;
    public HeartShapeView(Context context) {
        super(context);
    }

    public HeartShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HeartView);
        heartColor = ta.getColor(R.styleable.HeartView_heartColor, Color.WHITE);
        canvasColor = ta.getColor(R.styleable.HeartView_hCanvasColor, Color.RED);
        mPaint = new Paint();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(canvasColor);
        canvas.drawCircle(width / 2 , height / 2, width / 2, mPaint);
        mPaint.setColor(heartColor);
        //右半边心
        mPath.moveTo(width / 2, height * (5f / 12f - 1f /15f ));
        mPath.cubicTo(width * 3f / 4f, height * (1f / 4f - 1f /15f ), width * 5 / 6, height * (2f / 3f - 1f /15f), width / 2, height * (3f / 4f - 1f /15f));
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
        //左半边心
        mPath.moveTo(width / 2, height * (5f / 12f - 1f /15f ));
        mPath.cubicTo(width / 4f, height * (1f / 4f - 1f /15f ), width / 6f, height * (2f / 3f - 1f /15f), width / 2, height * (3f / 4f - 1f /15f));
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
        //填充空隙
        mPath.moveTo(width / 2, height * (5f / 12f - 1f /15f ));
        mPath.lineTo(width / 2, height * (3f / 4f - 1f /15f));
        mPaint.setStrokeWidth(2);
        canvas.drawPath(mPath, mPaint);
    }
}

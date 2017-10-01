package com.example.cliderecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class MyCallBack extends ItemTouchHelper.SimpleCallback {
    static float SCALE_VALUE = 0.05f;
    static float TRAN_Y;
    OnSwipeListener mOnSwipeListener;
    OnSwipingListener mOnSwipingListener;
    RecyclerView mRecycler;
    int mHorizontalDeviation = 500; //水平偏移阈值，用于判断是否为垂直方向额滑动
    int mSwipeDir = ItemTouchHelper.UP; //手指滑动的方向(第一次滑动后setSwipeDirection()，所以要先定义初值)

    public MyCallBack(Context context, int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        TRAN_Y = (int) (15 * context.getResources().getDisplayMetrics().density);
    }

    public interface OnSwipeListener{
        void onSwiped(int position, int direction);
    }

    public interface OnSwipingListener {
        void onSwiping(float fraction, float dX);
    }

    public void setOnSwipeListener(OnSwipeListener onSwipeListener) {
        mOnSwipeListener = onSwipeListener;
    }

    public void setOnSwipingListener(OnSwipingListener onSwipingListener) {
        mOnSwipingListener = onSwipingListener;
    }

    public void setRV(RecyclerView recyclerView) {
        this.mRecycler = recyclerView;
    }

    public RecyclerView getRV(){
        return mRecycler;
    }

    public void setSwipeDirection(int direction) {
        mSwipeDir = direction;
    }
    public int getSwipeDirection(){
        return mSwipeDir;
    }


    public void notifyListener(int position, int direction){
        mOnSwipeListener.onSwiped(position, direction);
    }

    public boolean isSwipedHorizontal(View view) {

        float x = getRV().getWidth() / 2 - (view.getX() + view.getWidth() / 2);
        Log.d("deviation", x + "");
        return Math.abs(getRV().getWidth() / 2 - (view.getX() + view.getWidth() / 2)) < mHorizontalDeviation;
    }

    //设置回收的阈值
    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        if(isSwipedHorizontal(viewHolder.itemView)) {
            Log.d("Float", Float.MAX_VALUE + "");
            return Float.MAX_VALUE;
        }else {
            return 0.3f;
        }
    }
    //设置最大逃脱速度
    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        /*当滑动方向为UP或DOWN是无法逃脱*/
        if (getSwipeDirection() == ItemTouchHelper.UP | getSwipeDirection() == ItemTouchHelper.DOWN) {
            return Float.MAX_VALUE;
        }else {
            return super.getSwipeEscapeVelocity(defaultValue) / 10;
        }

    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        setSwipeDirection(direction);
        notifyListener(position, direction);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        int count = recyclerView.getChildCount();
        int level;
        float fraction;
        fraction = Math.abs(dX / getRV().getWidth());
        Log.d("fraction", fraction+"");
        for (int i = 0; i < count; i++) {
            View view = recyclerView.getChildAt(i);
            level = count - i;
            if (level == 1) {
                if (dX < -20) {
                    view.setRotation(15 * fraction);
                }else if (dX > 20) {
                    view.setRotation(-15 * fraction);
                }else {
                    view.setRotation(0);
                }
            }else if (level == 2) {
                view.setTranslationY(TRAN_Y - fraction *TRAN_Y );
                view.setScaleX(0.95f + fraction * SCALE_VALUE);
                view.setScaleY(0.95f + fraction * SCALE_VALUE);
            }else if (level == 3) {
                view.setTranslationY(2 * TRAN_Y - fraction *TRAN_Y);
                view.setScaleX(0.90f + fraction * SCALE_VALUE);
                view.setScaleY(0.90f + fraction * SCALE_VALUE);
            }else if (level == 4) {
                view.setTranslationY(3 * TRAN_Y - fraction *TRAN_Y );
                view.setScaleX(0.85f + fraction * SCALE_VALUE);
                view.setScaleY(0.85f + fraction * SCALE_VALUE);
                Log.d("done", "done");
            }
        }

        mOnSwipingListener.onSwiping(fraction, dX);
    }
}

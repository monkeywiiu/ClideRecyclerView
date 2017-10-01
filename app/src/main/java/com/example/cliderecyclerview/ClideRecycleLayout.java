package com.example.cliderecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class ClideRecycleLayout extends RecyclerView.LayoutManager {
    static int MAX_SHOW_CARD = 4;
    static float SCALE_VALUE = 0.05f;
    static int TRANS_Y;
    static int VIEW_H;
    static int VIEW_W;

    public ClideRecycleLayout(Context context) {
        TRANS_Y = (int) (15 * context.getResources().getDisplayMetrics().density);
    }

    public void setScale(View view) {
        this.VIEW_W = view.getWidth();
        this.VIEW_H = view.getHeight();
    }
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        detachAndScrapAttachedViews(recycler);
        int width, height;
        int level = 0; // 用于确定最顶端的4张团片
        int itemCount = getItemCount();
        setScale(recycler.getViewForPosition(0));
        for (int position = 0; position < itemCount; position ++) {
            View view = recycler.getViewForPosition(position);
            measureChildWithMargins(view, 0, 0);
            addView(view);
            Log.d("width", "=" + getWidth());
            width = (getWidth() - view.getMeasuredWidth())/2; //卡片的左上角x坐标
            height = (getHeight() - view.getMeasuredHeight())/2; //卡片的右上角y坐标
            layoutDecoratedWithMargins(view, width,
                    height, width + view.getMeasuredWidth(), height + view.getMeasuredHeight());
            view.setRotation(0);
            level = itemCount - position;
            //Log.d("level", "level=" + level);
            /*上层4张图片中2-4进行缩放平移*/
            if (level == 2) {
                view.setTranslationY(TRANS_Y);
                view.setScaleX(0.95f);
                view.setScaleY(0.95f);

            }else if (level == 3) {
                view.setTranslationY(TRANS_Y*2);
                view.setScaleX(0.9f);
                view.setScaleY(0.9f);

            }else if (level == 4 | level == 5) {
                view.setTranslationY(TRANS_Y*3);
                view.setScaleX(0.85f);
                view.setScaleY(0.85f);

            }else if (level > 5) {
                view.setScaleX(0.5f);
                view.setScaleY(0.5f);
            }
        }
    }
}

package com.example.cliderecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<FillCardBean> mData;
    public RecyclerView mRecyclerView;
    public MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mData = FillCardBean.initDatas();
        mAdapter = new MyAdapter(this, mData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new ClideRecycleLayout(this));
        MyCallBack myCallBack = new MyCallBack(this, 0,
                ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        myCallBack.setRV(mRecyclerView);
        myCallBack.setOnSwipeListener(new MyCallBack.OnSwipeListener() {
            @Override
            public void onSwiped(int position, int direction) {
                mData.add(0, mData.get(position));//加在第一个位置
                mData.remove(position + 1);//移除最后一个位置
                mAdapter.notifyDataSetChanged();
            }
        });
        myCallBack.setOnSwipingListener(new MyCallBack.OnSwipingListener() {
            @Override
            public void onSwiping(float fraction, float dX) {
                if (dX > 0) {
                    mAdapter.getLastViewHolder().heartShapeView.setAlpha(fraction);
                }else if (dX < 0){
                    mAdapter.getLastViewHolder().forkShapeView.setAlpha(fraction);
                }
            }

        });
        new ItemTouchHelper(myCallBack).attachToRecyclerView(mRecyclerView);
        //mAdapter.getLastViewHolder().
    }

}

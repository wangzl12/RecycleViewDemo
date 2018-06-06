package example.com.recycleviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.recycleviewdemo.adapter.MyAdapter;

/**
 * Created by wangzl on 2018/5/28.
 */

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecycleView;
    private LinearLayoutManager mLayoutManager;

    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mRecycleView.setLayoutManager(mLayoutManager);

        //new LinearSnapHelper().attachToRecyclerView(mRecycleView);
        new CustomSnapHelper().attachToRecyclerView(mRecycleView);

        mRecycleView.addItemDecoration(new SpaceItemDecoration(5));


        ArrayList<String> mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("文本" + i);
        }
        myAdapter = new MyAdapter(mData);
        mRecycleView.setAdapter(myAdapter);

        

//        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this,"点击了" +position,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });
        mRecycleView.addOnItemTouchListener(new RecyclerViewClickListener(this, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SecondActivity.this,"点击了" +position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}

package example.com.recycleviewdemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.recycleviewdemo.R;

/**
 * Created by wangzl on 2018/4/26.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    public ArrayList<String> datas = new ArrayList<>();

    public MyAdapter(ArrayList<String> datas) {
        this.datas = datas;
    }

    //创建新view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //将数据与界面进行绑定
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (datas.size() != 0) {


            holder.mTextView.setText(datas.get(position % datas.size()));

            if (mOnItemClickListener != null) {
                holder.mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position1 = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, position1);
                    }
                });

                holder.mImageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        int position1 = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(holder.itemView, position1);
                        return false;
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView mImageView;
        @BindView(R.id.tv)
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 定义接口回调
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}

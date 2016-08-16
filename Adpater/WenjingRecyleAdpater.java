package uml.yangwenjing.wenjingandroidlibrary.Adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import uml.yangwenjing.wenjingandroidlibrary.R;
import uml.yangwenjing.wenjingandroidlibrary.UI.WenjingRecycleAct;

/**
 * Created by yamengwenjing on 2016/8/3.
 */
public class WenjingRecyleAdpater extends RecyclerView.Adapter {

    private LayoutInflater mLayoutInflater;

    private String[] mTitles=null;

    public WenjingRecyleAdpater(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mTitles=new String[20];
        for (int i=0;i<20;i++){
            int index=i+1;
            mTitles[i]="item"+index;
        }
    }

    public WenjingRecyleAdpater(Context context,OnRecyclerItemClickListener listerer ) {
        mLayoutInflater = LayoutInflater.from(context);
        this.thisListener = listerer;
        this.mTitles=new String[20];
        for (int i=0;i<20;i++){
            int index=i+1;
            mTitles[i]="item"+index;
        }
    }

    /* 在这个方法里面 写你独立的viewholder，这样就分离了，和listview的returnView什么的那个
    一般在这里继承一个viewHolder 写一个自己的viewHolder 内部静态类就可以了

    */
    @Override
    public wenjingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new wenjingViewHolder(mLayoutInflater.inflate(R.layout.simple_text_item,parent,false)) ;
    }

    //这里是是你的viewHolder和数据连接的地方
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((wenjingViewHolder) holder).tx.setText(mTitles[position]);

        //为了回调函数，将list的postion传递进去
        ((wenjingViewHolder) holder).itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    //需要自定义ViewHOLDER ,来绑定以及设置view事件，如果不用回调函数，在这里设置事件即可，但这个方法是不科学的
//    public static class wenjingViewHolder extends RecyclerView.ViewHolder{
//
//        @BindView(R.id.list_test_single_item_text_view)
//        TextView tx;
//        public wenjingViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("NormalTextViewHolder", "onClick--> position = " + getAdapterPosition());
//                }
//            });
//        }
//    }

    //带观察者模式的接口
        public  class wenjingViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.list_test_single_item_text_view)
        TextView tx;
        public wenjingViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   if(thisListener!=null){
                       thisListener.onItemClick(itemView, (int)itemView.getTag());
                   }
                }
            });
        }
    }


    public void setThisListener(OnRecyclerItemClickListener thisListener) {
        this.thisListener = thisListener;
    }

    private  OnRecyclerItemClickListener thisListener;
    /**
     * 自定义RecyclerView 中item view点击回调方法
     */
    public interface OnRecyclerItemClickListener{
        /**
         * item view 回调方法
         * @param view  被点击的view
         * @param position 点击索引
         */
        void onItemClick(View view, int position);
    }

}

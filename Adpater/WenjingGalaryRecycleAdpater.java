package uml.yangwenjing.wenjingandroidlibrary.Adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uml.yangwenjing.wenjingandroidlibrary.R;
import uml.yangwenjing.wenjingandroidlibrary.libraryBean.GalleryModel;

/**
 * Created by yamengwenjing on 2016/8/16.
 */
public class WenjingGalaryRecycleAdpater extends RecyclerView.Adapter<WenjingGalaryRecycleAdpater.ViewHolder>{

    private List<GalleryModel> models;
    private LayoutInflater mInflater;

    public WenjingGalaryRecycleAdpater(Context context){
            models = new ArrayList<GalleryModel>();
            for(int i= 0; i<20;i++){
                int index = i+1;
                //知识点 可以直接把 r的一个值 当做int url传入给model 学到了
                models.add(new GalleryModel(R.drawable.ic_item_gallery,"item"+index));
            }
            mInflater = LayoutInflater.from(context);
    }

    // 这里创建item view ，然后用viewholder来承载
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleview_test_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleImage.setImageResource(models.get(position).getImgurl());
        holder.titleText.setText(models.get(position).getTitle());
        //给hodler itemView 一个position
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }



    public  class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recycle_test_item_img)
        ImageView titleImage;
        @BindView(R.id.recycle_test_TextView)
        TextView titleText;
        public ViewHolder(final View view){
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //调用回调函数
                    thisListener.onItemClickCallBack(view,(int)view.getTag());
                }
            });
        }
    }


    //含一个回调函数
    private galaryItemOnClckListener thisListener;

    public galaryItemOnClckListener getThisListener() {
        return thisListener;
    }

    public void setThisListener(galaryItemOnClckListener thisListener) {
        this.thisListener = thisListener;
    }


    public interface galaryItemOnClckListener{

        public void onItemClickCallBack(View view, int tagPostion);
    }

}

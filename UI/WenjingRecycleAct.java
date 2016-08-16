package uml.yangwenjing.wenjingandroidlibrary.UI;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import uml.yangwenjing.wenjingandroidlibrary.Adpater.WenjingGalaryRecycleAdpater;
import uml.yangwenjing.wenjingandroidlibrary.Adpater.WenjingRecyleAdpater;
import uml.yangwenjing.wenjingandroidlibrary.R;

public class WenjingRecycleAct extends AppCompatActivity {

    @BindView(R.id.recyclerView_one) RecyclerView wenjingTest;

    private LinearLayoutManager linerLayoutManager;
    private GridLayoutManager gridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenjing_recycle);
        ButterKnife.bind(this);
        linerLayoutManager = new LinearLayoutManager(this);
        //默认的线性布局可以设置方向
//        linerLayoutManager.setOrientation();
//        gridLayoutManager = new GridLayoutManager(this,3);

        wenjingTest.setLayoutManager(linerLayoutManager);

        //带回调函数的RECYCLEvIEW
//        WenjingRecyleAdpater thisAdpter = new WenjingRecyleAdpater(this, new WenjingRecyleAdpater.OnRecyclerItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Log.d("wenjing",""+position);
//            }
//        });

        linerLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        wenjingTest.setHasFixedSize(true);
        WenjingGalaryRecycleAdpater adpater = new WenjingGalaryRecycleAdpater(this);



        wenjingTest.setAdapter(adpater);


    }

}

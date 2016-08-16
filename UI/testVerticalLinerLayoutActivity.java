package uml.yangwenjing.wenjingandroidlibrary.UI;


import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import uml.yangwenjing.wenjingandroidlibrary.R;

public class testVerticalLinerLayoutActivity extends Activity {

    private VerticalLinearLayout mMianLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_vertical_liner_layout);

        mMianLayout = (VerticalLinearLayout) findViewById(R.id.id_main_ly);
        mMianLayout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener() {
            @Override
            public void onPageChange(int currentPage) {
//				mMianLayout.getChildAt(currentPage);
                Toast.makeText(testVerticalLinerLayoutActivity.this, "第" + (currentPage + 1) + "页", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

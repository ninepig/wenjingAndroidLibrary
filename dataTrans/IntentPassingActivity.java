package uml.yangwenjing.wenjingandroidlibrary.dataTrans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uml.yangwenjing.wenjingandroidlibrary.R;
import uml.yangwenjing.wenjingandroidlibrary.libraryBean.Question;

/*
http://blog.csdn.net/ahuier/article/details/8953017
 */
public class IntentPassingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_passing);


        //1 uesing intent to pass
//        Intent intent = new Intent(IntentPassingActivity.this, IntentReceivingActvity.class);
//
//        intent.putExtra("name", "AHuier");
//        intent.putExtra("age", 22);
//        intent.putExtra("address", "XiaMen");
//
//        startActivity(intent);

        // 2 using bundle
//        Intent intent = new Intent(IntentPassingActivity.this,IntentReceivingActvity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("name", "Ben");
//        bundle.putInt("age", 28);
//        bundle.putString("address", "China");
//        intent.putExtras(bundle);     //将bundle传入intent中。
        // using bean
        Question thisQuestion = new Question();
        Intent gotoQuestionDetailIntent = new Intent(IntentPassingActivity.this, IntentReceivingActvity.class);
        gotoQuestionDetailIntent.putExtra("class",thisQuestion);
        startActivity(gotoQuestionDetailIntent);

    }
}

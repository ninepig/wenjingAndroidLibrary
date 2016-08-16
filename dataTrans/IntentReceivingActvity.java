package uml.yangwenjing.wenjingandroidlibrary.dataTrans;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uml.yangwenjing.wenjingandroidlibrary.R;
import uml.yangwenjing.wenjingandroidlibrary.libraryBean.Question;

public class IntentReceivingActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_receiving_actvity);
        //1 intent receiving
//        Intent intent = getIntent();
//        int age = intent.getIntExtra("age", 0);
//        String name = intent.getStringExtra("name");
//        String address = intent.getStringExtra("address");

        //2 bundle receiving
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        String nameString = bundle.getString("name");
//        int age = bundle.getInt("age");
//        String addressString = bundle.getString("address");
        //3 using bean intent seriable
//        Intent thisIntent = getIntent();
//        Question thisQuestion = (Question) thisIntent.getSerializableExtra("class");


    }
}

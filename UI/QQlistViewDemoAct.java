package uml.yangwenjing.wenjingandroidlibrary.UI;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import uml.yangwenjing.wenjingandroidlibrary.R;

public class QQlistViewDemoAct extends Activity {


    private QQListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlist_view_demo);

        mListView = (QQListView) findViewById(R.id.id_listview);
        mDatas = new ArrayList<String>(Arrays.asList("HelloWorld", "Welcome", "Java", "Android", "Servlet", "Struts",
                "Hibernate", "Spring", "HTML5", "Javascript", "Lucene"));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);
        mListView.setDelButtonClickListener(new QQListView.DelButonClickListener()
        {
            @Override
            public void clickDeleteButton(final int position)
            {
                Toast.makeText(QQlistViewDemoAct.this, position + " : " + mAdapter.getItem(position), 1).show();
                mAdapter.remove(mAdapter.getItem(position));

            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(QQlistViewDemoAct.this, position + " : " + mAdapter.getItem(position), 1).show();
            }
        });
    }
}

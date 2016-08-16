package uml.yangwenjing.wenjingandroidlibrary.UserfulLibrary.butterKnife;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uml.yangwenjing.wenjingandroidlibrary.R;

/*
有了butterknife这个工具 就少了很多initview的过程 ,其实就是省略了你写 findviewById 外加还要强转的过程
1fucntion one bindView
2Bind String , drawable,color,Dimen  this kind of resource file
3 BindView in fragemtn
4 BindView in viewholder(adpater)
5 Bind 监听器
其实BK 最主要的功能还是省略了findviewbyid。很强
 */
public class ButterKnifeTestAct extends Activity {

    //fucntion 1
    @BindView(R.id.butterKnifetest_title)TextView title;
    @BindView(R.id.butterKnifetest_subtitle)TextView subTile;
    @BindView(R.id.click)Button button;
    @BindView(R.id.click2)Button button2;


    // function 2
    @BindString(R.string.test_buttonknife_string) String testString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife_test);
        ButterKnife.bind(this);


    }

    //fucntion 5 绑定xml上的事件，xml必须声明了以后才能绑定！，不过可以强制转换参数类型，可以通过注解详细的看清楚方法的意义
    //把多个view绑定同一个方法， 不过感觉意义还是不大。。对于以前可能有一定的功能，但现在android可以不需要监听器，应该是实现了bk的一部分功能
    //自己搞错了！！用了插件以后，感觉起飞啊！ 非常好用  ，完全拜托了xml文件的设置
    @OnClick({R.id.click,R.id.click2})
       public void click(View view){

        Log.d("wenjingButter", "clicked");
    }



}

package uml.yangwenjing.wenjingandroidlibrary.dataTrans;

import android.util.JsonReader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by yamengwenjing on 2016/7/27.
 * http://www.cnblogs.com/tianzhijiexian/p/4246497.html
 */
public class GsonTools {

    private String getStrFromAssets(String name) {
        String strData = null;
//        try {
//
//            //这个是必须在activity中使用的类
//            InputStream inputStream = getAssets().open(name);
//            byte buf[] = new byte[1024];
//
//            inputStream.read(buf);
//            strData = new String(buf);
//            strData = strData.trim();

//        } catch (IOException e) {
//            // TODO 自动生成的 catch 块
//            e.printStackTrace();
//        }
        Log.d("Json data", strData);
        return strData;
    }


    /**
     * @description 将json序列变为list对象
     *
     */
    private void GsonTest05() {
        Type listType = new TypeToken<ArrayList<Foo01>>(){}.getType();
        ArrayList<Foo01> foos = new Gson().fromJson(getStrFromAssets("Json03"), listType);
        for (int i = 0; i < foos.size(); i++) {
            System.out.println("name ["+ i +"] = " + foos.get(i).id);
        }
    }


    /**
     * @description 解析为数组
     *
     */
    private void GsonTest04() {
        Foo01[] foos = new Gson().fromJson(getStrFromAssets("Json03"), Foo01[].class);
        System.out.println("name01 = " + foos[0].id);
        System.out.println("name02 = " + foos[1].id);
        // 这时候想转成List的话调用如下方法
        // List<Foo> foosList = Arrays.asList(foos);
    }

    /**
     * @description 解析嵌套是Json数据
     *
     */
    private void GsonTest03() {
        Foo03 foo = new Gson().fromJson(getStrFromAssets("Json02"), Foo03.class);
        System.out.println("name = " + foo.childFoo.name);
    }

    /**
     * @description 当json中有日期对象时，通过定义构建格式生成需要的date对象
     * 当json数据命名和java命名产生不一致时，可以通过注释的方式实现更换名字，更方便进行代码处理
     */
    private void GsonTest02() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期的格式，遇到这个格式的数据转为Date对象
        Gson gson = gsonBuilder.create();
        Foo02 foo = gson.fromJson(getStrFromAssets("Json01"), Foo02.class);
        System.out.println("date = " + foo.get_my_date());
    }

    /**
     * @description 将json数据解析为类对象
     */
    private void GsonTest01() {
        Foo01 foo = new Gson().fromJson(getStrFromAssets("Json01"), Foo01.class);
        System.out.println("id = " + foo.id);
    }

    /**
     * @description 通过JsonReader解析Json对象
     *
     * @web http://codego.net/480737/
     */
    private void jsonReaderTest() {
        // 这里的Json放到string中，所以加上了转译
        String jsonData = "[{\"username\":\"name01\",\"userId\":001},{\"username\":\"name02\",\"userId\":002}]";

        JsonReader reader = new JsonReader(new StringReader(jsonData));
        reader.setLenient(true); // 在宽松模式下解析
        try {
            reader.beginArray(); // 开始解析数组（包含一个或多个Json对象）
            while (reader.hasNext()) { // 如果有下一个数据就继续解析
                reader.beginObject(); // 开始解析一个新的对象
                while (reader.hasNext()) {
                    String tagName = reader.nextName(); // 得到下一个属性名
                    if (tagName.equals("username")) {
                        System.out.println(reader.nextString());
                    } else if (tagName.equals("userId")) {
                        System.out.println(reader.nextString());
                    }
                }
                reader.endObject(); // 结束对象的解析
            }
            reader.endArray(); // 结束解析当前数组
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}

package uml.yangwenjing.wenjingandroidlibrary.RestroFitLearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import uml.yangwenjing.wenjingandroidlibrary.R;

public class testActvityForRestrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_actvity_for_restrofit);
        // Create a very simple REST adapter which points the GitHub API endpoint.

        new Thread(new Runnable() {
            @Override
            public void run() {
                GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

                // Fetch and print a list of the contributors to this library.
                // call 是Restrofit的一个bean ，应该是定义了一次调用
                Call<List<Contributor>> call =
                        client.contributors("huandu", "facebook");

                List<Contributor> contributors;

                try {
                    contributors = call.execute().body();
                    Log.d("wenjing",contributors.size()+"");
                } catch (IOException e) {
                    // handle errors
                }

//                for (Contributor contributor : contributors) {
//                    System.out.println(
//                            contributor.login + " (" + contributor.contributions + ")");
//                }
            }
        }).start();


    }
}

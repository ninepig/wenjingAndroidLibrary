package uml.yangwenjing.wenjingandroidlibrary.RestroFitLearning;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by yamengwenjing on 2016/7/28.
 */
public interface GitHubClient {

    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );


}

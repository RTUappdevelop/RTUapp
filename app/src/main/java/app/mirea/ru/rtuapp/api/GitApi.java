package app.mirea.ru.rtuapp.api;

import java.util.List;

import app.mirea.ru.rtuapp.models.GitHubRepo;
import app.mirea.ru.rtuapp.models.Token;
import app.mirea.ru.rtuapp.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GitApi {


    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<Token> getToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String secret,
            @Field("code") String code
    );


    // метод получения юзера по токену
    @GET("/user")
    Call<User>getCurrentUser();


    // и получаем его репозитории
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>>getReposForUser(@Path("user") String user);


    //получаем имя в био
    @GET("/users/{user}/")
    Call<User>getUserFullName();

}
package app.mirea.ru.rtuapp.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import app.mirea.ru.rtuapp.models.GitHubRepo;
import app.mirea.ru.rtuapp.models.Token;
import app.mirea.ru.rtuapp.models.User;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private  GitApi gitApi;

    public ServiceGenerator(String base_url, final String accessToken){
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

            @NonNull
            @Override
            public okhttp3.Response intercept(@NonNull Chain chain) throws IOException {
                okhttp3.Request.Builder builder = chain.request().newBuilder();

                if (accessToken != null) {

                    builder.addHeader("Authorization", "token " + accessToken);

                }

                return chain.proceed(builder.build());
            }

        }).readTimeout(60, TimeUnit.SECONDS).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        gitApi = retrofit.create(GitApi.class);
    }



    // получаем репо пользователя
    public void getRepos(String userName, Callback<List<GitHubRepo>> callback) {

        gitApi.getReposForUser(userName).enqueue(callback);

    }



    // получаем токен
    public void getToken(String clientId, String clientSecret, String code, Callback<Token> callback) {

        gitApi.getToken(clientId, clientSecret, code).enqueue(callback);

    }



    //Получаем пользователя по токену

    public void getCurrentUser(Callback<User> callback) {
        gitApi.getCurrentUser().enqueue(callback);
    }

    public void getUserFullName(Callback<User> callback){
        gitApi.getUserFullName();
    }

}

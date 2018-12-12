package app.mirea.ru.rtuapp.api;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GitApi {
    @GET("login/oauth/authorize")
    Call<Response> loadRepo();
}
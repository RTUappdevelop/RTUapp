package app.mirea.ru.rtuapp;

import android.app.Application;

import app.mirea.ru.rtuapp.api.GitApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static GitApi gitApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        gitApi = retrofit.create(GitApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static GitApi getGitApi() {
        return gitApi;
    }
}

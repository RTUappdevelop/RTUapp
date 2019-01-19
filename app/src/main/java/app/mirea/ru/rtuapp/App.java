package app.mirea.ru.rtuapp;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import app.mirea.ru.rtuapp.api.GitApi;
import app.mirea.ru.rtuapp.api.ServiceGenerator;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static ServiceGenerator serviceGenerator;
    private static SharedPreferences sharPrefs;
    private static final String AUTH_URL = "https://github.com/";
    private static final String BASE_URL = "https://api.github.com/";
    private static String username;
    private static String token;

    @Override
    public void onCreate() {
        super.onCreate();

        sharPrefs = getSharedPreferences("preferences", MODE_PRIVATE);

        if (token != null) {
            setBaseServiceGenerator();
        } else {
            setAuthServiceGenerator();
        }
    }


    public static ServiceGenerator get_serviceGenerator(){
        return serviceGenerator;
    }

    // cеттеры и геттеры юернейма. Достаем данные
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        App.username = username;
        sharPrefs.edit().putString(String.valueOf(R.string.username), username).apply();
    }

    public static void setAuthServiceGenerator() {
        serviceGenerator = new ServiceGenerator(AUTH_URL, null);
    }

    public static void setBaseServiceGenerator() {
        serviceGenerator = new ServiceGenerator(BASE_URL, getAccessToken());
    }

    // сохраняем токен на устройстве
    public static void setAccessToken(String token) {
        App.token = token;
        sharPrefs.edit().putString(String.valueOf(R.string.token), token).apply();
    }

    // забираем токен с утройства
    public static String getAccessToken() {
        return sharPrefs.getString(String.valueOf(R.string.token), null);
    }
}

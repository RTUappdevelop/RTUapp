package app.mirea.ru.rtuapp;

import android.app.Application;

import app.mirea.ru.rtuapp.api.GitApi;
import app.mirea.ru.rtuapp.api.ServiceGenerator;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {

    private static GitApi gitApi;
    private Retrofit retrofit;
    private static ServiceGenerator serviceGenerator;
    private static final String AUTH_URL = "https://github.com/";
    private static final String BASE_URL = "https://api.github.com/";
    public static String username;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        gitApi = retrofit.create(GitApi.class); //Создаем объект, при помощи которого будем выполнять запросы
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
//        sharPrefs.edit().putString(String.valueOf(R.string.username), username).apply();
    }

    public static void setAuthServiceGenerator() {
//        serviceGeneraror = new ServiceGeneraror(AUTH_URL, null);
    }

    public static void setBaseServiceGenerator() {
        serviceGenerator = new ServiceGenerator(BASE_URL, getAccessToken());
    }

    // сохраняем токен на устройстве
    public static void setAccessToken(String token) {
//        sharPrefs.edit().putString(String.valueOf(R.string.token), token).apply();
    }

    // забираем токен с утройства
    public static String getAccessToken() {
//        return sharPrefs.getString(String.valueOf(R.string.token), null);
        return username;
    }
}

package app.mirea.ru.rtuapp;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import app.mirea.ru.rtuapp.models.Token;
import app.mirea.ru.rtuapp.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class GitLoginPresenter extends AppCompatActivity {

    private Button signIn;
    private Button skipAuth;
    private TextView gitUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_login);

        signIn = findViewById(R.id.signIn);
        skipAuth = findViewById(R.id.skip_button);
        gitUserName = findViewById(R.id.gitUserName);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String urlRedirect = "https://github.com/login/oauth/authorize?client_id="
                        + getResources().getString(R.string.client_id)
                        + "&scope=repo&redirect_uri="
                        + "app.mirea.ru.rtuapp://callback";
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(urlRedirect));
                startActivity(intent);
            }
        });

        skipAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(GitLoginPresenter.this, MainPresenter.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith("app.mirea.ru.rtuapp://callback")) {

            String code = uri.getQueryParameter("code");
            App.get_serviceGenerator().getToken(getResources().getString(R.string.client_id),
                    getResources().getString(R.string.client_secret),
                    code,
                    new Callback<Token>() {


                @Override
                public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {

                    if (response.isSuccessful()) {

                        assert response.body() != null;
                        Toast.makeText(GitLoginPresenter.this, "Токен = " + response.body().getToken(), Toast.LENGTH_LONG).show();
                        App.setAccessToken(response.body().getToken());
                        App.setBaseServiceGenerator();

                        getUserName();

                    } else {

                        Log.d(String.valueOf(LOG), "ошибка получения токена" + response.code());
                        System.out.println("Error code " +  response.code());

                        try {
                            assert response.errorBody() != null;
                            Log.d(String.valueOf(LOG), "ошибка получения токена " + response.errorBody().string());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(GitLoginPresenter.this,
                            "Ошибка onFailure  " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }


    private void getUserName() {
        App.get_serviceGenerator().getCurrentUser(new Callback<User>() {

            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    System.out.println("Логин " + response.body().getLogin());
                    App.setUsername(response.body().getLogin());
                    // если все ок, то отправляемся в мэйнактивити
                    Intent intent = new Intent(GitLoginPresenter.this, MainPresenter.class);
                    startActivity(intent);
                    finishAffinity();

                } else {
                    try {
                        assert response.errorBody() != null;
                        Log.d(String.valueOf(LOG), "Ошибка получения имени" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                t.printStackTrace();
            }

        });
    }
}

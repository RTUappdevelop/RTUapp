package app.mirea.ru.rtuapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.mirea.ru.rtuapp.App;
import app.mirea.ru.rtuapp.R;
import app.mirea.ru.rtuapp.adapters.ReposAdapter;
import app.mirea.ru.rtuapp.models.GitHubRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposFragment extends Fragment {

    ReposAdapter reposAdapter;
    RecyclerView reposList;
    List<GitHubRepo> gitHubRepoList = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repos, container, false);

        reposList = view.findViewById(R.id.reposList);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        reposList.setLayoutManager(linearLayoutManager);
        reposAdapter = new ReposAdapter(getContext(), gitHubRepoList);
        reposList.setAdapter(reposAdapter);
        loadRepos();

    }

    private void loadRepos() {

        App.get_serviceGenerator().getRepos(App.getUsername(), new Callback<List<GitHubRepo>>() {

            @Override
            public void onResponse(@NonNull Call<List<GitHubRepo>> call, @NonNull Response<List<GitHubRepo>> response) {

                if(response.isSuccessful()){

                    gitHubRepoList.clear();
                    assert response.body() != null;
                    gitHubRepoList.addAll(response.body());
                    reposAdapter.notifyDataSetChanged();
                    System.out.println("LastTest" + App.getUsername());
                    String test = App.getUsername() + "";



                } else {
                    try {
                        Toast.makeText(getContext(), "Нет подключения к интернету, повторите позже"
                                + App.getUsername(),Toast.LENGTH_SHORT).show();
                        assert response.errorBody() != null;
                        Log.d("Error",  response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<GitHubRepo>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });



    }
}

package app.mirea.ru.rtuapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import app.mirea.ru.rtuapp.R;
import app.mirea.ru.rtuapp.models.GitHubRepo;


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder>{

    List<GitHubRepo> gitHubRepoList;
    LayoutInflater inflater;

    public ReposAdapter(Context context, List<GitHubRepo> gitHubRepoList) {
        this.gitHubRepoList = gitHubRepoList;
        this.inflater = LayoutInflater.from(context);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView repos_name;
        private ViewHolder(View itemView) {
            super(itemView);
            repos_name = itemView.findViewById(R.id.repos_name);
        }
    }


    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_repositories, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReposAdapter.ViewHolder viewHolder, int position) {
        viewHolder.repos_name.setText(gitHubRepoList.get(position).getName());
    }

    @Override
    public int getItemCount() {return gitHubRepoList.size(); }
}

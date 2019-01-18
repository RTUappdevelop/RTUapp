package app.mirea.ru.rtuapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import app.mirea.ru.rtuapp.R;
import app.mirea.ru.rtuapp.models.GitHubRepo;


public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RepoViewHolder>{

    class RepoViewHolder extends RecyclerView.ViewHolder {
        private TextView repos_name;
        private RepoViewHolder(View itemView) {
            super(itemView);

            repos_name = itemView.findViewById(R.id.repos_name);
        }
    }

    private List<GitHubRepo> gitHubRepoList;

    public ReposAdapter(List<GitHubRepo> gitHubRepoList) {
        this.gitHubRepoList = gitHubRepoList;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_repositories,
                viewGroup,
                false);
        return new RepoViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder repoViewHolder, int i) {

        repoViewHolder.repos_name.setText(gitHubRepoList.get(i).getName());

    }

    @Override
    public int getItemCount() {

        if (gitHubRepoList != null) {
            return gitHubRepoList.size();
        } else {
            return 0;
        }

    }
}

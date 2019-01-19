package app.mirea.ru.rtuapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.mirea.ru.rtuapp.MVP.MainModel;
import app.mirea.ru.rtuapp.fragments.CameraFragment;
import app.mirea.ru.rtuapp.fragments.ContactsFragment;
import app.mirea.ru.rtuapp.fragments.DeviceInfoFragment;
import app.mirea.ru.rtuapp.fragments.MapsFragment;
import app.mirea.ru.rtuapp.fragments.ReposFragment;
import app.mirea.ru.rtuapp.models.GitHubRepo;

public class MainPresenter extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<GitHubRepo> gitHubRepoList = new ArrayList<>();
    private Toolbar toolbar;
    private TextView gitUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //добавляем имя пользователя в боковое меню
//        gitUserName.setText(App.getUsername());

        // проверка разрешений
        MainModel.checkPermission(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReposFragment()).commit();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View head = navigationView.getHeaderView(0);
        gitUserName = head.findViewById(R.id.gitUserName);
        gitUserName.setText(App.getUsername());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_maps) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new MapsFragment()).commit();
        } else if (id == R.id.nav_device_info) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new DeviceInfoFragment()).commit();
        }else if (id == R.id.nav_contacts) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ContactsFragment()).commit();
        }else if (id == R.id.nav_camera) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new CameraFragment()).commit();
        }else if (id == R.id.nav_repos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReposFragment()).commit();
        }
        ///Здесь был субарист с субарином

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

package app.mirea.ru.rtuapp.MVP;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import app.mirea.ru.rtuapp.App;
import app.mirea.ru.rtuapp.adapters.ReposAdapter;
import app.mirea.ru.rtuapp.models.GitHubRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel {

    public static void checkPermission(Context context){

        int permissionContacts = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.READ_CONTACTS);
        int permissionMaps = ContextCompat.checkSelfPermission(context.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionContacts == PackageManager.PERMISSION_DENIED) {
            if (permissionMaps == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[] {Manifest.permission.READ_CONTACTS,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            }
            ActivityCompat.requestPermissions((Activity) context, new String[] {Manifest.permission.READ_CONTACTS},
                    0);
        } else {
            Log.d("ContactsPresenter", "PERMISSION_GRANTED");
        }

        if (permissionMaps == PackageManager.PERMISSION_DENIED) {
            if (permissionContacts == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions((Activity) context,
                        new String[] {Manifest.permission.READ_CONTACTS,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);
            }
            ActivityCompat.requestPermissions((Activity) context,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    0);
        } else {
            Log.d("Maps", "PERMISSION_GRANTED");
        }
    }
}

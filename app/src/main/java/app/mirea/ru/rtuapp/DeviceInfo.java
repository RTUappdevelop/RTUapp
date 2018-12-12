package app.mirea.ru.rtuapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DeviceInfo extends AppCompatActivity {
    private static final String TAG = "DeviceInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_device_info);
        Log.i(TAG, "BRAND: " + Build.BRAND ); //имя бренда
        Log.i(TAG, "MANUFACTURER: " + Build.MANUFACTURER ); //производитель устройства
        Log.i(TAG, "MODEL: " + Build.MODEL ); //название продукта(модели)
        Log.i(TAG, "PRODUCT: " + Build.PRODUCT ); //имя продукта
        Log.i(TAG, "ID: " + Build.ID ); // id или номер списка изменений
    }
}

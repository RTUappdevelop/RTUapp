package app.mirea.ru.rtuapp;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class DeviceInfo extends AppCompatActivity {
    //private static final String TAG = "DeviceInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device_info);
        TextView Info = (TextView) findViewById(R.id.Info);
        Info.setText("Device Information");

        TextView ID = findViewById(R.id.ID);
        ID.setText("Android Device ID");
        TextView DevID = findViewById(R.id.DevID);
        DevID.setText(Build.ID);

        TextView Brand = findViewById(R.id.Brand);
        Brand.setText("BRAND");
        TextView DevBrand = findViewById(R.id.DevBrand);
        DevBrand.setText(Build.BRAND);

        TextView Man = findViewById(R.id.Man);
        Man.setText("MANUFACTURER");
        TextView DevMan = findViewById(R.id.DevMan);
        DevMan.setText(Build.MANUFACTURER);

        TextView Model = findViewById(R.id.Model);
        Model.setText("Model");
        TextView DevModel = findViewById(R.id.DevModel);
        DevModel.setText(Build.MODEL);

        TextView Product = findViewById(R.id.Product);
        Product.setText("PRODUCT");
        TextView DevProduct = findViewById(R.id.DevProduct);
        DevProduct.setText(Build.PRODUCT);

    }
}

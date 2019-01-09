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

        TextView ID = (TextView) findViewById(R.id.ID);
        ID.setText("Android Device ID");
        TextView DevID = (TextView) findViewById(R.id.DevID);
        DevID.setText(Build.ID);

        TextView Brand = (TextView) findViewById(R.id.Brand);
        Brand.setText("BRAND");
        TextView DevBrand = (TextView) findViewById(R.id.DevBrand);
        DevBrand.setText(Build.BRAND);

        TextView Man = (TextView) findViewById(R.id.Man);
        Man.setText("MANUFACTURER");
        TextView DevMan = (TextView) findViewById(R.id.DevMan);
        DevMan.setText(Build.MANUFACTURER);

        TextView Model = (TextView) findViewById(R.id.Model);
        Model.setText("Model");
        TextView DevModel = (TextView) findViewById(R.id.DevModel);
        DevModel.setText(Build.MODEL);

        TextView Product = (TextView) findViewById(R.id.Product);
        Product.setText("PRODUCT");
        TextView DevProduct = (TextView) findViewById(R.id.DevProduct);
        DevProduct.setText(Build.PRODUCT);

    }
}

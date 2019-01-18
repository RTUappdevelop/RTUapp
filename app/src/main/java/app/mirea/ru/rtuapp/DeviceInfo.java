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

        TextView ID = (TextView) findViewById(R.id.ID);
        ID.setText("  Android Device ID");
        TextView DevID = (TextView) findViewById(R.id.DevID);
        DevID.setText("   " + Build.ID);

        TextView Brand = (TextView) findViewById(R.id.Brand);
        Brand.setText("  Brand");
        TextView DevBrand = (TextView) findViewById(R.id.DevBrand);
        DevBrand.setText("   " + Build.BRAND);

        TextView Man = (TextView) findViewById(R.id.Man);
        Man.setText("  Manufacturer");
        TextView DevMan = (TextView) findViewById(R.id.DevMan);
        DevMan.setText("   " + Build.MANUFACTURER);

        TextView Model = (TextView) findViewById(R.id.Model);
        Model.setText("  Model");
        TextView DevModel = (TextView) findViewById(R.id.DevModel);
        DevModel.setText("   " + Build.MODEL);

        TextView Product = (TextView) findViewById(R.id.Product);
        Product.setText("  Product");
        TextView DevProduct = (TextView) findViewById(R.id.DevProduct);
        DevProduct.setText("   " + Build.PRODUCT);

        TextView PO = (TextView) findViewById(R.id.PO);
        PO.setText("  Software Build Number");
        TextView DevPO = (TextView) findViewById(R.id.DevPO);
        DevPO.setText("   " + Build.VERSION.INCREMENTAL);

        TextView VerAndroid = (TextView) findViewById(R.id.VerAndroid);
        VerAndroid.setText("  Android Version");
        TextView DevVerAndroid = (TextView) findViewById(R.id.DevVerAndroid);
        DevVerAndroid.setText("   " + Build.VERSION.RELEASE);

        TextView VerSDK = (TextView) findViewById(R.id.VerSDK);
        VerSDK.setText("  SDK Version");
        TextView DevVerSDK = (TextView) findViewById(R.id.DevVerSDK);
        DevVerSDK.setText("   " + Build.VERSION.SDK_INT);

    }
}

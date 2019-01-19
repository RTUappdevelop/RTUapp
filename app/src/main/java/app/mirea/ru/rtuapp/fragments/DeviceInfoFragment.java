package app.mirea.ru.rtuapp.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.mirea.ru.rtuapp.R;

public class DeviceInfoFragment extends Fragment {

    private DeviceInfoViewModel mViewModel;
    private TextView ID;
    private TextView devID;
    private TextView brand;
    private TextView devBrand;
    private TextView man;
    private TextView devMan;
    private TextView model;
    private TextView devModel;
    private TextView product;
    private TextView devProduct;
    private TextView PO;
    private TextView DevPO;
    private TextView VerAndroid;
    private TextView DevVerAndroid;
    private TextView VerSDK;
    private TextView DevVerSDK;

    public static DeviceInfoFragment newInstance() {
        return new DeviceInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.device_info_fragment, container, false);
        ID = view.findViewById(R.id.ID);
        devID = view.findViewById(R.id.DevID);
        brand = view.findViewById(R.id.Brand);
        devBrand = view.findViewById(R.id.DevBrand);
        man = view.findViewById(R.id.Man);
        devMan = view.findViewById(R.id.DevMan);
        model = view.findViewById(R.id.Model);
        devModel = view.findViewById(R.id.DevModel);
        product = view.findViewById(R.id.Product);
        devProduct = view.findViewById(R.id.DevProduct);
        PO = view.findViewById(R.id.PO);
        DevPO = view.findViewById(R.id.DevPO);
        VerAndroid = view.findViewById(R.id.VerAndroid);
        DevVerAndroid = view.findViewById(R.id.DevVerAndroid);
        VerSDK = view.findViewById(R.id.VerSDK);
        DevVerSDK = view.findViewById(R.id.DevVerSDK);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DeviceInfoViewModel.class);

        ID.setText("Android Device ID");
        devID.setText(Build.ID);
        brand.setText("Brand");
        devBrand.setText(Build.BRAND);
        man.setText("Manufacturer");
        devMan.setText(Build.MANUFACTURER);
        model.setText("Model");
        devModel.setText(Build.MODEL);
        product.setText("Product");
        devProduct.setText(Build.PRODUCT);
        PO.setText("Software Build Number");
        DevPO.setText(Build.VERSION.INCREMENTAL);
        VerAndroid.setText("Android Version");
        DevVerAndroid.setText(Build.VERSION.RELEASE);
        VerSDK.setText("SDK Version");
        DevVerSDK.setText(String.valueOf(Build.VERSION.SDK_INT));
    }

}

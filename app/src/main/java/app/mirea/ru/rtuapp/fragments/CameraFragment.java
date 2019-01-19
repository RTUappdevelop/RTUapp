package app.mirea.ru.rtuapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.mirea.ru.rtuapp.R;
import app.mirea.ru.rtuapp.adapters.ReposAdapter;
import app.mirea.ru.rtuapp.models.GitHubRepo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraFragment extends Fragment implements SensorEventListener{

    private Button mButton;
    private ImageView mImage;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView xValue;
    private TextView yValue;
    private TextView zValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_camera, container, false);

        mButton = view.findViewById(R.id.button);
        mImage = view.findViewById(R.id.imageView2);
        xValue = view.findViewById(R.id.xValue);
        yValue = view.findViewById(R.id.yValue);
        zValue = view.findViewById(R.id.zValue);

        sensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select Picture"), 0);
            }
        } );

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 0) {
                Uri selectedImageUri = data.getData();

                mImage.setVisibility(View.VISIBLE);
                mImage.setImageURI(selectedImageUri);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){
        xValue.setText("xValue: " + sensorEvent.values[0]);
        yValue.setText("yValue: " + sensorEvent.values[1]);
        zValue.setText("zValue: " + sensorEvent.values[2]);
    }

}

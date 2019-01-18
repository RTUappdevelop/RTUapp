package app.mirea.ru.rtuapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class GalleryAndSensors extends AppCompatActivity implements SensorEventListener{

    Button mButton;
    ImageView mImage;

    private static final int SELECT_PICTURE = 100;
    private SensorManager sensorManager;
    Sensor accelerometer;

    TextView xValue, yValue, zValue, acceler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mButton = findViewById(R.id.button);
        mImage = findViewById(R.id.imageView2);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(GalleryAndSensors.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        xValue = findViewById(R.id.xValue);
        yValue = findViewById(R.id.yValue);
        zValue = findViewById(R.id.zValue);
        acceler = findViewById(R.id.accel);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        } );

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();

                mImage.setVisibility(View.VISIBLE);
                mImage.setImageURI(selectedImageUri);
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i){

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent){

        acceler.setText("Параметры датчика Акселерометр");
        xValue.setText("Первый параметр (x): " + sensorEvent.values[0]);
        yValue.setText("Второй параметр (y): " + sensorEvent.values[1]);
        zValue.setText("Третий параметр (z): " + sensorEvent.values[2]);
    }

}
package app.mirea.ru.rtuapp;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CameraActivity extends AppCompatActivity {

    Button mButton;
    ImageView mImage;

    private static final int SELECT_PICTURE = 100;
    private SensorManager mSenManager;
    private Sensor mAccelerometer;
    private TextView mAzimuth;
    private TextView mPitch;
    private TextView mRoll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mButton = findViewById(R.id.button);
        mImage = findViewById(R.id.imageView2);


        mSenManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSenManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mAzimuth = findViewById(R.id.textViewAzimuth);
        mPitch = findViewById(R.id.textViewPitch);
        mRoll = findViewById(R.id.textViewRoll);

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

                mImage.setImageURI(selectedImageUri);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSenManager.unregisterListener((SensorEventListener) this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mSenManager.registerListener((SensorEventListener) this, mAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
        // Не используется
    }

    public void onSensorChanged(SensorEvent event) {
        float valueAzimuth = event.values[0];
        float valuePitch = event.values[1];
        float valueRoll = event.values[2];

        mAzimuth.setText("Azimuth: " + String.valueOf(valueAzimuth));
        mPitch.setText("Pitch: " + String.valueOf(valuePitch));
        mRoll.setText("Roll: " + String.valueOf(valueRoll));
    }

}

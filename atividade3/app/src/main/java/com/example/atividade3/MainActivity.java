package com.example.atividade3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView coordinateX, coordinateY, coordinateZ;

    float sensorX, sensorY, sensorZ;
    float lastCoordinateX, lastCoordinateY, lastCoordinateZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sensorX = 0;
        this.sensorY = 0;
        this.sensorZ = 0;

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        coordinateX = (TextView) findViewById(R.id.coordX);
        coordinateY = (TextView) findViewById(R.id.coordY);
        coordinateZ = (TextView) findViewById(R.id.coordZ);
    }
    @Override
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            lastCoordinateX = sensorX;
            lastCoordinateY = sensorY;
            lastCoordinateZ = sensorZ;

            sensorX = event.values[0];
            sensorY = event.values[1];
            sensorZ = event.values[2];

            coordinateX.setText("X: " + String.valueOf(sensorX));
            coordinateY.setText("Y: " + String.valueOf(sensorY));
            coordinateZ.setText("Z: " + String.valueOf(sensorZ));
            transitionToSecondAct();
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public void transitionToSecondAct(){
        Intent i = new Intent(this, MainActivity2.class);
        if((Math.abs(sensorX - lastCoordinateX) > 1.0) || (Math.abs(sensorY - lastCoordinateY) > 10) || (Math.abs(sensorZ - lastCoordinateZ) > 1.0)){
            startActivity(i);
        }
    }
}
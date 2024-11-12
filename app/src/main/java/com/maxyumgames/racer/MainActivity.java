package com.maxyumgames.racer;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MusicManager musicManager;
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!supportES2()) {
            Toast.makeText(this, "OpenGL ES 2.0 is not supported", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Инициализация MusicManager
        musicManager = new MusicManager(this);
        musicManager.play(); // Запуск музыки

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);
        glSurfaceView.setRenderer(new OpenGLRenderer(this));
        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        musicManager.pause(); // Пауза музыки при приостановке активности
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        musicManager.play();
        glSurfaceView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        musicManager.release(); // Освобождение ресурсов при завершении
    }

    private boolean supportES2() {
        ActivityManager activityManager =
                (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        return (configurationInfo.reqGlEsVersion >= 0x20000);
    }

}
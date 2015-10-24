package com.healthapp.healthapp;

import android.hardware.Camera;

import java.util.List;

/**
 * Created by Clive on 10/23/2015.
 */
public class CameraUtils {
    public CameraUtils() {
    }

    public static Camera getCameraInstance() {
        return getCameraInstance(-1);
    }

    public static Camera getCameraInstance(int cameraId) {
        Camera c = null;

        try {
            if(cameraId == -1) {
                c = Camera.open();
            } else {
                c = Camera.open(cameraId);
            }
        } catch (Exception var3) {
            ;
        }

        return c;
    }

    public static boolean isFlashSupported(Camera camera) {
        if(camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            if(parameters.getFlashMode() == null) {
                return false;
            } else {
                List supportedFlashModes = parameters.getSupportedFlashModes();
                return supportedFlashModes != null && !supportedFlashModes.isEmpty() && (supportedFlashModes.size() != 1 || !((String)supportedFlashModes.get(0)).equals("off"));
            }
        } else {
            return false;
        }
    }
}

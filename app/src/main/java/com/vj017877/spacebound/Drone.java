package com.vj017877.spacebound;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.vj017877.spacebound.GameView.scRatioX;
import static com.vj017877.spacebound.GameView.scRatioY;


/**
 * Is a drone themed enemy entity
 *
 * @author - Andreas Paridis
 * @version - Submition
 * @see Enemy - Abstract enemy class
 */
public class Drone extends Enemy {

    /* Drones Models */
    Bitmap drone;

    Drone(Resources resources) {

        /* Bitmap Decoding from drawable folder */
        drone = BitmapFactory.decodeResource(resources, R.drawable.drone);

        /* Scaling */
        this.width = (int) ((drone.getWidth() / 2) * scRatioX);
        this.height = (int) ((drone.getHeight() / 2) * scRatioY);

        /* Resizing of model*/
        drone = Bitmap.createScaledBitmap(drone, width, height, false);

        this.y = -height; //sets off screen
        this.speed = 10; //speed
    }

    @Override
    Bitmap getModel() {
        return drone;
    }

    @Override
    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}

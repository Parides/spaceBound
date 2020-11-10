package com.vj017877.spacebound;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.vj017877.spacebound.GameView.scRatioX;
import static com.vj017877.spacebound.GameView.scRatioY;

/**
 * Is an asteroid themed enemy entity
 *
 * @author - Andreas Paridis
 * @version - Submition
 * @see Enemy - Abstract enemy class
 */
public class Asteroid extends Enemy {

    /* Asteroid Model */
    Bitmap asteroid; // Model of asteroid

    Asteroid(Resources resources) {
        /*  Bitmap Decoding from Drawable folder */

        /* Moving Animation */
        asteroid = BitmapFactory.decodeResource(resources, R.drawable.asteroid1); // Anim 1


        /* Model Scaling */
        this.width = (int) ((asteroid.getWidth() / 2) * scRatioX);
        this.height = (int) ((asteroid.getHeight() / 2) * scRatioY);

        /* Resizing of model */
        asteroid = Bitmap.createScaledBitmap(asteroid, width, height, false);

        this.speed = 10; //speed
        this.y = -height; // Asteroid to be placed off screen
    }


    @Override
    Bitmap getModel() {
        return asteroid; /* initial animation*/
    }

    @Override
    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

}

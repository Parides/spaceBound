package com.vj017877.spacebound;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.vj017877.spacebound.GameView.scRatioX;
import static com.vj017877.spacebound.GameView.scRatioY;

public class Ufo extends Enemy {

    /* UFO model */
    Bitmap ufo1, ufo2;

    Ufo(Resources resources) {

        /* Bitmap Decoding */
        ufo1 = BitmapFactory.decodeResource(resources, R.drawable.ufo);
        ufo2 = BitmapFactory.decodeResource(resources, R.drawable.ufo2);

        /* Model Scaling */
        this.width = (int) ((ufo1.getWidth() / 2) * scRatioX);
        this.height = (int) ((ufo2.getHeight() / 2) * scRatioY);

        /* Resizing of model */
        ufo1 = Bitmap.createScaledBitmap(ufo1, width, height, false);
        ufo2 = Bitmap.createScaledBitmap(ufo2, width, height, false);

        this.speed = 10; //speed
        this.y = -height;
    }

    @Override
    Bitmap getModel() {
        //If the animation is the first
        if (this.modelAnimation == 1) {
            this.modelAnimation++;
            return ufo1; // Change animation
        }
        //If the animation is the second
        if (this.modelAnimation == 2) {
            this.modelAnimation++;
            return ufo2; // Change animation
        }
        this.modelAnimation = 1; // reset animation counter
        return ufo1; // initial animation
    }

    @Override
    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }
}

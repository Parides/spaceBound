package com.vj017877.spacebound;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.vj017877.spacebound.GameView.scRatioX;
import static com.vj017877.spacebound.GameView.scRatioY;

/**
 * This is the bullet shot by the player
 *
 * @author - Andreas Paridis
 * @version - Submition
 * @see Friendly - Friendly Abstract class
 */
public class Missile extends Friendly {

    /* Missile Model */
    Bitmap missile;

    Missile(Resources resources) {

        /*  Bitmap Decoding from Drawable folder */
        missile = BitmapFactory.decodeResource(resources, R.drawable.missile);

        /* Scaling of model */
        this.width = (int) ((missile.getWidth() / 2) * scRatioX);
        this.height = (int) ((missile.getHeight() / 2) * scRatioY);

        /* Resize of model */
        missile = Bitmap.createScaledBitmap(missile, width, height, false);
    }

    @Override
    Bitmap getRocket() {
        return null;
    }

    /**
     * Responsible for the collision shape of the object using the appropriate parameters
     *
     * @return the collision rectangle shape
     */
    @Override
    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    @Override
    Bitmap getDeadModel() {
        return null;
    }
}

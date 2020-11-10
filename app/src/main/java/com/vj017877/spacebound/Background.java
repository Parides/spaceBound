package com.vj017877.spacebound;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Contains the different backgrounds for the level
 * Is responsible for rendering the appropriate background
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class Background {

    int x = 0; // background position X
    int y = 0; // background position Y
    Bitmap mainBackground; // background picture

    /**
     * Is responsible for the decoding of the appropriate images
     *
     * @param screenX   - Screen X size
     * @param screenY   - Screen Y size
     * @param resources - Used to decode bitmap from drawable resources
     * @param lvl       - Determinant of the level
     */
    Background(int screenX, int screenY, Resources resources, int lvl) {

        if (lvl == 1) {
            //Decodes the bitmap from the drawable folder
            mainBackground = BitmapFactory.decodeResource(resources, R.drawable.background3);
            //Scales the bitmap decoded above to the screen X and Y dimensions without taking filter
            mainBackground = Bitmap.createScaledBitmap(mainBackground, screenX, screenY, false);
        }

        if (lvl == 2) {
            //Decodes the bitmap from the drawable folder
            mainBackground = BitmapFactory.decodeResource(resources, R.drawable.background4);
            //Scales the bitmap decoded above to the screen X and Y dimensions without taking filter
            mainBackground = Bitmap.createScaledBitmap(mainBackground, screenX, screenY, false);
        }

        if (lvl == 3) {
            //Decodes the bitmap from the drawable folder
            mainBackground = BitmapFactory.decodeResource(resources, R.drawable.background2);
            //Scales the bitmap decoded above to the screen X and Y dimensions without taking filter
            mainBackground = Bitmap.createScaledBitmap(mainBackground, screenX, screenY, false);
        }

    }
}

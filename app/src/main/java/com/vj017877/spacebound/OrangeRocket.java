package com.vj017877.spacebound;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.vj017877.spacebound.GameView.scRatioX;
import static com.vj017877.spacebound.GameView.scRatioY;

/**
 * Is an orange themed friendly (player) entity
 *
 * @author - Andreas Paridis
 * @version - Submition
 * @see Friendly - Abstract friendly class
 */
public class OrangeRocket extends Friendly {


    /* Moving Model */
    Bitmap rocket1, rocket2;

    /* Shooting Model */
    Bitmap rocketShoot1, rocketShoot2, rocketShoot3, rocketShoot4;

    /* Dead Model */
    Bitmap rocketDead;

    GameView2 gameView; //The gameView object

    /**
     * Responsible handling the rocker (player) models scaling, animation as well as its movement
     *
     * @param gameView  - The current instance of the gameView class
     * @param screenY   - Where the rocker will move
     * @param resources - resources
     */
    OrangeRocket(GameView2 gameView, int screenY, Resources resources) {

        this.gameView = gameView;

        /*  Bitmap Decoding from Drawable folder */

        /* Moving Animation */
        rocket1 = BitmapFactory.decodeResource(resources, R.drawable.orange_player); // Animation 1
        rocket2 = BitmapFactory.decodeResource(resources, R.drawable.orange_player2); // Animation 2

        /* Shooting Animation */
        rocketShoot1 = BitmapFactory.decodeResource(resources, R.drawable.orange_shoot1); // Animation 1
        rocketShoot2 = BitmapFactory.decodeResource(resources, R.drawable.orange_shoot2); // Animation 2
        rocketShoot3 = BitmapFactory.decodeResource(resources, R.drawable.orange_shoot3); // Animation 3
        rocketShoot4 = BitmapFactory.decodeResource(resources, R.drawable.orange_shoot4); // Animation 4

        /* Dead Animation */
        rocketDead = BitmapFactory.decodeResource(resources, R.drawable.dead); //dead animation


        /* Get Model dimensions */
        this.width = (int) ((rocket1.getWidth() / 2) * scRatioX);
        this.height = (int) ((rocket1.getHeight() / 2) * scRatioY);

        /* Resizing of model */

        /* Moving Animation */
        rocket1 = Bitmap.createScaledBitmap(rocket1, width, height, false); // Model 1
        rocket2 = Bitmap.createScaledBitmap(rocket2, width, height, false); // Model 2

        /* Shooting Animation */
        rocketShoot1 = Bitmap.createScaledBitmap(rocketShoot1, width, height, false);
        rocketShoot2 = Bitmap.createScaledBitmap(rocketShoot2, width, height, false);
        rocketShoot3 = Bitmap.createScaledBitmap(rocketShoot3, width, height, false);
        rocketShoot4 = Bitmap.createScaledBitmap(rocketShoot4, width, height, false);

        /* Dead Animation */
        rocketDead = Bitmap.createScaledBitmap(rocketDead, width * 2
                , height * 2, false);


        this.y = screenY / 2; //Places the model in the centre of the screen
        this.x = (int) (128 * scRatioX); // Scaled margin for X axis (not spawn inside border)
    }

    /**
     * Responsible for changing the animation of the rocket
     *
     * @return the appropriate rocket animation
     */
    @Override
    Bitmap getRocket() {

        // If there are missiles left to fire
        if (missilesShot != 0) {
            //If the animation is the first
            if (shootAnimationCounter == 1) {
                shootAnimationCounter++;
                return rocketShoot1; //Change to animation 1
            }
            //If the animation is the second
            if (shootAnimationCounter == 2) {
                shootAnimationCounter++;
                return rocketShoot2; //Change to animation 1
            }

            //If the animation is the third
            if (shootAnimationCounter == 3) {
                shootAnimationCounter++;
                return rocketShoot3; //Change to animation 1
            }

            //If the animation is the fourth
            if (shootAnimationCounter == 4) {
                shootAnimationCounter++;
                return rocketShoot4; //Change to animation 1
            }

            shootAnimationCounter = 1; // Repeat animation
            missilesShot--; // Reduce the missiles left
            gameView.reloadRocket();
        }
        // If the animation is the initial
        if (animationCounter == 0) {
            animationCounter++;
            return rocket1; // Change to animation 2
        } else {
            animationCounter--;
            return rocket2; // Change to animation 1
        }

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

    /**
     * For the dead model
     *
     * @return the dead model
     */
    @Override
    Bitmap getDeadModel() {
        return rocketDead;
    }
}

package com.vj017877.spacebound;

import android.graphics.Bitmap;
import android.graphics.Rect;

public abstract class Friendly {

    /* Rocket Positions */
    int x;
    int y;

    /* Rocket Movement */
    public boolean isAscending = false;

    /* Moving Model */
    int width; // Rocket model width
    int height; // Rocket model height
    int animationCounter = 0; // Keeps track of switching animations

    /* Shooting Model */
    int missilesShot = 0; // counts the missiles animation
    int shootAnimationCounter = 1; // Keeps track of switching animations

    Friendly(){
        //nothing
    }

    /**
     * Responsible for changing the animation of the rocket
     *
     * @return the appropriate rocket animation
     */
    abstract Bitmap getRocket();

    /**
     * Responsible for the collision shape of the object using the appropriate parameters
     *
     * @return the collision rectangle shape
     */
    abstract Rect getCollisionShape ();

    /**
     * For the dead model
     * @return the dead model
     */
    abstract Bitmap getDeadModel();


}

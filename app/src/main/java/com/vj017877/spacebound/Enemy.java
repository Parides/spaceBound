package com.vj017877.spacebound;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * This is the abstract class of all enemy entities
 * Contains all required variables and methods for the classes
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public abstract class Enemy {

    /* Model Status */
    public int speed; //enemy speed
    public boolean wasShot = true;
    public int modelAnimation = 1;
    /* Asteroid Positions */
    int x;
    int y;
    int width; // enemy model width
    int height; // enemy model height


    Enemy() {
        //All enemy models must be adjusted separately
    }

    /**
     * Responsible for changing the animation of the models (NOT IMPLEMENTED FOR ALL MODELS)
     *
     * @return the appropriate model animation
     */
    abstract Bitmap getModel();

    /**
     * Responsible for the collision shape of the object using the appropriate parameters
     *
     * @return the collision rectangle shape
     */
    abstract Rect getCollisionShape();
}

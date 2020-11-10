package com.vj017877.spacebound;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Is responsible for the functionality of the About tab of the game
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class AboutActivity extends AppCompatActivity {

    AboutActivity aboutActivity; // Declaration of the view

    /**
     * Responsible for creating the view's functionality upon creation
     *
     * @param savedInstanceState - For restoring purposes
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState); //saved instance

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN); //sets game into Fullscreen

        Point point = new Point(); // contains the size of the screen on X and Y axis
        getWindowManager().getDefaultDisplay().getSize(point); // gets the Size of screen

        // Initializes gameview with the correct screen dimesnions
        aboutActivity = new AboutActivity();
        setContentView(R.layout.activity_about); //sets layout to the about view

        // When back arrow is pressed
        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                startActivity(new Intent(AboutActivity.this, MainActivity.class));
                finish();// closes current view
            }
        });
    }
}

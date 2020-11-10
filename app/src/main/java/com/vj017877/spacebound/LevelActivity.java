package com.vj017877.spacebound;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Is responsible for the functionality of the level selection tab of the game
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class LevelActivity extends AppCompatActivity {

    LevelActivity levelActivity;

    /**
     *  Responsible for creating the view's functionality upon creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN); //sets game into Fullscreen

        Point point = new Point(); // contains the size of the screen on X and Y axis
        getWindowManager().getDefaultDisplay().getSize(point); // gets the Size of screen

        // Initializes gameview with the correct screen dimesnions
        levelActivity = new LevelActivity();
        setContentView(R.layout.activity_level);

        findViewById(R.id.lvl1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelActivity.this, GameActivity.class));
                finish();
            }
        });

        findViewById(R.id.lvl2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelActivity.this, GameActivity2.class));
                finish();
            }
        });

        findViewById(R.id.lvl3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LevelActivity.this, GameActivity3.class));
                finish();
            }
        });

        // When back arrow is pressed
        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                startActivity(new Intent(LevelActivity.this, MainActivity.class));
                finish();
            }
        });


    }
}

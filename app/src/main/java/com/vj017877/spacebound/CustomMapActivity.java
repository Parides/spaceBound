package com.vj017877.spacebound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Is responsible for the functionality of the Custom map tab of the game
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class CustomMapActivity extends AppCompatActivity {


    CustomMapActivity customMapActivity; // Declaration of the view

    /**
     * Responsible for creating the view's functionality upon creation
     *
     * @param savedInstanceState - For restoring purposes
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // saved instancd

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN); //sets game into Fullscreen


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //Sets activity to fullscreen

        // Initializes gameview with the correct screen dimesnions
        customMapActivity = new CustomMapActivity();
        setContentView(R.layout.activity_custom_map);

        /* The following methods change the alpha of the non selected
         * button to half to indicate which was selected
         */

        // FIRST ROW

        findViewById(R.id.bg_black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.bg_light).setAlpha(0.5f); //fades other button
                findViewById(R.id.bg_purple).setAlpha(0.5f); //fades other button

                findViewById(R.id.bg_black).setAlpha(1.0f); //keeps un faded
            }
        });

        findViewById(R.id.bg_light).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.bg_black).setAlpha(0.5f);
                findViewById(R.id.bg_purple).setAlpha(0.5f);

                findViewById(R.id.bg_light).setAlpha(1.0f);

            }
        });

        findViewById(R.id.bg_purple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.bg_black).setAlpha(0.5f);
                findViewById(R.id.bg_light).setAlpha(0.5f);

                findViewById(R.id.bg_purple).setAlpha(1.0f);

            }
        });

        // SECOND ROW

        findViewById(R.id.p_red).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.player_blue).setAlpha(0.5f);
                findViewById(R.id.p_orange).setAlpha(0.5f);

                findViewById(R.id.p_red).setAlpha(1.0f);

            }
        });

        findViewById(R.id.player_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.p_red).setAlpha(0.5f);
                findViewById(R.id.p_orange).setAlpha(0.5f);

                findViewById(R.id.player_blue).setAlpha(1.0f);

            }
        });

        findViewById(R.id.p_orange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.p_red).setAlpha(0.5f);
                findViewById(R.id.player_blue).setAlpha(0.5f);

                findViewById(R.id.p_orange).setAlpha(1.0f);

            }
        });

        // SECOND ROW

        findViewById(R.id.enemy_asteroid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.enemy_drone).setAlpha(0.5f);
                findViewById(R.id.enemy_ufo).setAlpha(0.5f);

                findViewById(R.id.enemy_asteroid).setAlpha(1.0f);

            }
        });

        findViewById(R.id.enemy_drone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.enemy_asteroid).setAlpha(0.5f);
                findViewById(R.id.enemy_ufo).setAlpha(0.5f);

                findViewById(R.id.enemy_drone).setAlpha(1.0f);

            }
        });

        findViewById(R.id.enemy_ufo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.enemy_drone).setAlpha(0.5f);
                findViewById(R.id.enemy_asteroid).setAlpha(0.5f);

                findViewById(R.id.enemy_ufo).setAlpha(1.0f);

            }
        });

        // When back arrow is pressed
        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                startActivity(new Intent(CustomMapActivity.this, MainActivity.class));
                finish();// closes current view
            }
        });
    }
}

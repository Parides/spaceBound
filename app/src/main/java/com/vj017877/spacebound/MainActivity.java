package com.vj017877.spacebound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Is responsible for the functionality of the main menu tab of the game
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class MainActivity extends AppCompatActivity {

    private boolean isMute; //determines if game is muted

    /**
     * Handles the activities that take place whenever a user clicks on a button
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //Sets activity to fullscreen

        setContentView(R.layout.activity_main);


        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LevelActivity.class));
                finish();
            }
        });


        // Shared preferences variable containing all need-to-be shared resources
        final SharedPreferences sharedPreferences = getSharedPreferences("game",
                MODE_PRIVATE);

        /* VOLUME CONTROL */
        sharedPreferences.getBoolean("isMute", false); //Mute state of the game

        final ImageView volumeCtrl = findViewById(R.id.VolCtrl); //imageview of the volume button

        // Sets the appropriate volume icon
        if (isMute) {
            volumeCtrl.setImageResource(R.drawable.ic_volume_off_white_24dp); // Set vol off icon
        } else {
            volumeCtrl.setImageResource(R.drawable.ic_volume_up_white_24dp); // Set vol on icon
        }

        // Changes game mute status and volume icon
        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isMute = !isMute; //Interchanges from mute to non mute

                // Sets the appropriate volume icon
                if (isMute) {
                    volumeCtrl.setImageResource(R.drawable.ic_volume_off_white_24dp);
                } else {
                    volumeCtrl.setImageResource(R.drawable.ic_volume_up_white_24dp);
                }

                // Changes the game status appropriately (isMute boolean)
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isMute", isMute); //adds adds if mute or not
                editor.apply(); //applies

            }
        });


        //when user clicks on about button change activity to appropriate
        findViewById(R.id.aboutbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                finish();
            }
        });

        //when user clicks on the highs cores button change activity to appropriate
        findViewById(R.id.scores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HighscoreActivity.class));
                finish();
            }
        });

        //when user clicks on the high scores button change activity to appropriate
        findViewById(R.id.tutorial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TutorialActivity.class));
                finish();
            }
        });

        //when user clicks on custom
        findViewById(R.id.custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomMapActivity.class));
                finish();
            }
        });

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

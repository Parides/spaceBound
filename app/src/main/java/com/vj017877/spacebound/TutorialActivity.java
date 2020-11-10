package com.vj017877.spacebound;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class TutorialActivity extends AppCompatActivity {

    TutorialActivity tutorialActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN); //sets game into Fullscreen

        Point point = new Point(); // contains the size of the screen on X and Y axis
        getWindowManager().getDefaultDisplay().getSize(point); // gets the Size of screen

        // Initializes gameview with the correct screen dimesnions
        tutorialActivity = new TutorialActivity();
        setContentView(R.layout.activity_tutorial);

        // When back arrow is pressed
        findViewById(R.id.arrow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to home screen
                startActivity(new Intent(TutorialActivity.this, MainActivity.class));
                finish();// closes current view
            }
        });
    }
}

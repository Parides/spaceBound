package com.vj017877.spacebound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Is reposnible for handling everything that can happen in level 3
 * Handles animation, moving, scoring, shooting, ending
 *
 * @author - Andreas Paridis
 * @version - Submition
 */
public class GameView3 extends SurfaceView implements Runnable {

    public static float scRatioX; // The screen X ratio to scale for all screen resolutions
    public static float scRatioY; // The screen Y ratio to scale for all screen resolutions
    Canvas gameCanvas; // Canvas to draw text and other assets
    private Thread thread; // this is the game thread
    private Random rnd; // contains random numbers
    private SharedPreferences sharedPreferences; //for high score saving
    private GameActivity3 gameActivity; //the game activity
    /* Sounds */
    private SoundPool soundPool;
    private int sound;
    /* Game status */
    private boolean isRunning; // determines the state of the game
    private boolean isHit = false; // to indicate when game is over
    private int score = 0; // current score of player
    /* Backgrounds */
    private Background bg1, bg2; // hold the game backgrounds
    /* Screen Sizes */
    private int screenX; // Screen X dimensions
    private int screenY; // Screen Y dimensions
    /* Paint class that holds the */
    private Paint paint;

    /* Player */
    private GreenRocket rocket; // Rocket (player) object

    /* Missiles */
    private List<Missile> missileList; // Will contain all missiles fired

    /* Asteroids */
    private Drone[] droneList; //List of all the asteroids present in map


    public GameView3(GameActivity3 gameActivity, int screenX, int screenY) {
        super(gameActivity);

        this.gameActivity = gameActivity; //sets the game activity

        rnd = new Random(); //random variable

        // Private mode hides preference from other apps
        sharedPreferences = gameActivity.getSharedPreferences("game", Context.MODE_PRIVATE);

        /* Sounds */
        // if the sdk is higher than the set
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        sound = soundPool.load(gameActivity, R.raw.sfx_laser2, 1); //sound


        missileList = new ArrayList<>(); //initialization of array list

        /* Its an array instead of a list because there can only be 4 at a time*/
        droneList = new Drone[4]; //initialization of the list

        this.screenX = screenX; // gets X dimension
        this.screenY = screenY; // gets Y dimension

        scRatioX = 1920f / screenX;
        scRatioY = 1080f / screenY;

        // Initializes the background using the screen X and Y dimensions as well as resources
        bg1 = new Background(screenX, screenY, getResources(), 3);
        bg2 = new Background(screenX, screenY, getResources(), 3);

        rocket = new GreenRocket(this, screenY, getResources()); // Instantiation of rocket

        /*
         * The background will infinitely loop so two backgrounds are created so they loop
         * around each other
         */
        bg2.x = screenX; //The second background is set off screen

        paint = new Paint(); // Initialization of the pain object

        /* Score text */
        paint.setTextSize(128); //Sets text on screen
        paint.setColor(Color.WHITE); //Color White

        // Add 4 asteroids to map
        for (int i = 0; i < 4; i++) {
            Drone a = new Drone(getResources()); //create new asteroid
            droneList[i] = a; //add asteroid to list
        }

    }

    /**
     * Responsible for the actions happening when the
     * game thread is running
     */
    @Override
    public void run() {

        // While the game is running
        while (isRunning) {
            update(); // updates screen
            draw(); // draws elements on screen
            sleep(); // screen when needed
        }
    }

    /**
     * Responsible for updating every active element of the running game
     */
    private void update() {
        /* Backgrounds move by 10 pixels to the left each frame */
        bg1.x -= 10 * scRatioX; // Scaled according to device using ratio
        bg2.x -= 10 * scRatioX; // Scaled according to device using ratio

        // When background gets off bounds it is set to the end of the x axis
        //Checks by using the width of the background to determine if its out of bounds
        if (bg1.x + bg1.mainBackground.getWidth() < 0) {
            bg1.x = screenX; // puts on end of screen
        }

        if (bg2.x + bg2.mainBackground.getWidth() < 0) {
            bg2.x = screenX; // puts on end of screen
        }

        // if the rocket is ascending
        if (rocket.isAscending) {
            rocket.y -= 30 * scRatioY; // Move upwards by 30 pixels
        } else {
            rocket.y += 30 * scRatioY; // Move downwards by 30 pixels
        }

        /* Off bounds preventions */
        if (rocket.y < 0) {
            rocket.y = 0; //sets back to 0 when it goes off screen
        }
        if (rocket.y > screenY - rocket.height) {
            rocket.y = screenY - rocket.height; //sets back to top when it goes off screen
        }

        List<Missile> dump = new ArrayList<>(); // Not needed missiles

        // Drawing of missiles and movement
        for (Missile m : missileList) {
            // if offscreen
            if (m.x > screenX) {
                dump.add(m);
            }
            m.x += 30 * scRatioX; // Move to the left by 30 pixels

            // Checks if missiles hit asteroid
            for (Drone u : droneList) {

                // if they collide
                if (Rect.intersects(u.getCollisionShape(), m.getCollisionShape())) {
                    score += 2; //give the player 10 points
                    u.x = -500; //puts off screen - triggers reposition action belows
                    m.x = screenX + 500; //puts off screen - triggers dump action
                    u.wasShot = true;
                }
            }
        }

        // Remove bullets
        for (Missile m : dump) {
            missileList.remove(m);
        }

        // Draw Asteroids
        for (Drone a : droneList) {
            a.x -= a.speed;

            // if bird is off screen
            if (a.x + a.width < 0) {

                //if the bird was not shot and is on screen
                if (!a.wasShot) {
                    isHit = true;
                    return;
                }
                int speedLimit = (int) (30 * scRatioX); // asteroid speed limit
                a.speed = rnd.nextInt(speedLimit); // Random speed inside bounds

                /* Enemy AI Feature */
                // increases enemy object speed based on score
                if (score < 30) {
                    do {
                        a.speed = rnd.nextInt(speedLimit); // Random speed inside bounds
                    }
                    while (a.speed < 8 * scRatioX || a.speed > 10 * scRatioX);
                }
                if (score > 30 && score < 100) {
                    do {
                        a.speed = rnd.nextInt(speedLimit); // Random speed inside bounds
                    }
                    while (a.speed < 10 * scRatioX || a.speed > 18 * scRatioX);
                }

                if (score > 100) {
                    do {
                        a.speed = rnd.nextInt(speedLimit); // Random speed inside bounds
                    }
                    while (a.speed < 20 * scRatioX || a.speed > 30 * scRatioX);
                }

                a.x = screenX; // set off screen (right)

                //check if asteroid already exists at position
                /*do{
                    // new random location in bounds
                    a.y = rnd.nextInt(screenY - (a.height + 20));
                }
                while (a.isHere(a.y));*/

                do {
                    a.y = rnd.nextInt(screenY - (a.height + 20));
                }
                while (a.y < 150);
                a.wasShot = false;
            }

            if (Rect.intersects(a.getCollisionShape(), rocket.getCollisionShape())) {
                isHit = true;
                return;
            }
        }
        rocket.missilesShot++;
    }

    /**
     * Responsible for drawing all the elements on the canvas of the game
     */
    private void draw() {
        //ensures that surface object is initialised successfully
        if (getHolder().getSurface().isValid()) {
            gameCanvas = getHolder().lockCanvas(); // Returns the current canvas displayed

            /* Background */
            gameCanvas.drawBitmap(bg1.mainBackground, bg1.x, bg1.y, paint); // Draws BG on canvas
            gameCanvas.drawBitmap(bg2.mainBackground, bg2.x, bg2.y, paint); // Draws BG on canvas

            /* Score Previews */
            paint.setTextSize(40f); // Change text size
            gameCanvas.drawText("Current Score: " + score, 50, 50, paint); // Score text

            gameCanvas.drawText("Previous Best: " +
                    sharedPreferences.getInt("highscorelvl3",
                            0), 50, 90, paint);

            /* Rocket */
            gameCanvas.drawBitmap(rocket.getRocket(), rocket.x, rocket.y, paint); // Draws rocket

            /* Missiles */
            for (Missile m : missileList) {
                gameCanvas.drawBitmap(m.missile, m.x, m.y, paint); //Draws all missiles
            }

            /* Asteroids */
            for (Drone roid : droneList) {
                gameCanvas.drawBitmap(roid.getModel(), roid.x, roid.y, paint); // Draws asteroid
            }
            if (isHit) {
                isRunning = false;
                // draws dead model on canvas
                gameCanvas.drawBitmap(rocket.getDeadModel(), rocket.x, rocket.y, paint);
                drawGameOver();
                drawScore();
                getHolder().unlockCanvasAndPost(gameCanvas);//draw canvas on screen
                isHighScore(); // saves if NEW highscore
                exitWait(); // Waits seconds before return to home screen
                return;
            }

            getHolder().unlockCanvasAndPost(gameCanvas);// show canvas on the screen
        }
    }

    void drawGameOver() {
        Paint gameOver = new Paint();
        gameOver.setTextSize(100);
        gameOver.setTextAlign(Paint.Align.CENTER);
        gameOver.setColor(Color.WHITE);
        gameCanvas.drawText("GAME OVER", screenX / 2, screenY / 2, gameOver);
    }

    void drawScore() {
        if (isHighScore()) {
            Paint highScore = new Paint();
            highScore.setTextSize(70);
            highScore.setTextAlign(Paint.Align.CENTER);
            highScore.setColor(Color.WHITE);
            gameCanvas.drawText("New High Score : " + score, screenX / 2
                    , screenY / 2 + 100, highScore);
        } else {
            Paint currScore = new Paint();
            currScore.setTextSize(70);
            currScore.setTextAlign(Paint.Align.CENTER);
            currScore.setColor(Color.WHITE);
            gameCanvas.drawText("Your Score " + score, screenX / 2, screenY / 2 + 100
                    , currScore);
        }
    }

    private void exitWait() {
        try {
            Thread.sleep(2000); // 2 seconds sleep
            gameActivity.startActivity(new Intent(gameActivity
                    , MainActivity.class)); //Switch from current activity to main activity
            gameActivity.finish();//Finish Game Activity (current)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsible of updating the new high score whenever the old is passed
     */
    private boolean isHighScore() {
        if (sharedPreferences.getInt("highscorelvl3", 0) < score) {

            SharedPreferences.Editor editor = sharedPreferences.edit(); //edit the preferences
            editor.putInt("highscorelvl3", score); // put the new high score
            editor.apply(); // save the score
            return true;
        }
        return false;
    }

    /**
     * Responsible for the fps of the game
     */
    private void sleep() {
        try {
            thread.sleep(17); //1000ms / 17 ms = 60, hence 60 fps
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsible for the actions taken when the game resumes
     */
    public void resume() {
        isRunning = true; // game is running
        thread = new Thread(this); // initialization of the thread
        thread.start(); // begins running
    }

    /**
     * Responsible for the actions taken when the game (thread) is paused
     */
    public void pause() {
        try {
            isRunning = false; // game is paused
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Responsible for the actions happening when user taps on screen during game
     *
     * @param event - the action happening
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // tap screen
                rocket.isAscending = true; //going up
                break;
            case MotionEvent.ACTION_UP: // lift finger off screen
                rocket.isAscending = false; // going down
                break;
        }
        return true; // so it is always triggered when user presses on screen
    }

    /**
     * Responsible for the missile positions and handling
     */
    public void reloadRocket() {

        if (!sharedPreferences.getBoolean("isMute", false)) {
            soundPool.play(sound, 1, 1, 1, 0, 1);
        }
        Missile missile = new Missile(getResources()); // New missile object
        missile.x = rocket.x + rocket.width; // Sets its position to the middle of the rocket
        missile.y = rocket.y + (rocket.height / 2); // Near end of flight
        missileList.add(missile); //adds the missile to the list
    }
}

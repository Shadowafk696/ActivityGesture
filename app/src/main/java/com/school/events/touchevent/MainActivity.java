package com.school.events.touchevent;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{


    private static final String DEBUG_TAG = "Gestures";
    private GestureDetectorCompat mDetector;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);

        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        changeBackgroundColor(Color.LTGRAY);
//   Implement text and color in the Event.
        TextView textView = (TextView) findViewById(R.id.hello_gesture);
        textView.setText(" Down 6: ");
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        //   Implement text and color in the Event.
        changeBackgroundColor(Color.RED);
        TextView textView = (TextView) findViewById(R.id.hello_gesture);
        textView.setText("Long Press :v");

        Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent event1, MotionEvent event2, float distanceX,
                            float distanceY) {
        //   Implement text and color in the Event.
        changeBackgroundColor(Color.RED);
        Log.d(DEBUG_TAG, "onScroll: " + event1.toString() + event2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        //   Implement text and color in the Event.
        changeBackgroundColor(Color.GREEN);


        TextView textView = (TextView) findViewById(R.id.hello_gesture);
        textView.setText(" Press :D");

        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        //   Implement text and color in the Event.
        changeBackgroundColor(Color.MAGENTA);
        TextView textView = (TextView) findViewById(R.id.hello_gesture);
        textView.setText(" UP  :( ");
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        //   Implement color in the Event.
        changeBackgroundColor(Color.BLUE);
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        changeBackgroundColor(Color.CYAN);
       //   Implement text and color in the Event (it's a background).
        TextView textView = (TextView) findViewById(R.id.hello_gesture);
        textView.setText(" Double Tap Event >:v ");
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        changeBackgroundColor(Color.YELLOW);
        //   Implement text and color in the Event.
        TextView textView = (TextView) findViewById(R.id.hello_gesture);
        textView.setText(" Single -.-");
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }

    public void changeBackgroundColor(int color) {
        // Be sure to call the superclass implementation
        View view  = findViewById(R.id.main_layout);
        Drawable background = view.getBackground();
        int colorFrom = Color.TRANSPARENT;
        if (background instanceof ColorDrawable)
            colorFrom = ((ColorDrawable) background).getColor();
        int colorTo = color;
        // Instance different value in the color
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250); // milliseconds
        colorAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            //Indicate the view of background
            public void onAnimationUpdate(ValueAnimator animator) {
                View view  = findViewById(R.id.main_layout);
                view.setBackgroundColor((int) animator.getAnimatedValue());
            }

        });
        colorAnimation.start();


//        view.setBackgroundColor(color);
    }
}

package puf.example.tetris;

import android.view.MotionEvent;
import android.widget.Toast;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;


import java.util.Calendar;

class Finger implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    float x;
    float y;
    boolean active;

    private GestureDetectorCompat gestureDetector;
    static final float SCROLL_THRESHOLD = 10;

    static public Finger finger;

    static boolean move=false;

    static boolean othercase = false;

    static void setup() {
            finger = new Finger();
    }


  @Override
  public boolean onDoubleTap(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onSingleTapConfirmed(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onDoubleTapEvent(MotionEvent e) {
    return true;
  }

  @Override
  public boolean onDown(MotionEvent e) {
    return false;
  }

  @Override
  public void onShowPress(MotionEvent e) {

  }

  @Override
  public boolean onSingleTapUp(MotionEvent e) {
    return false;
  }

  @Override
  public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
    return false;
  }

  @Override
  public void onLongPress(MotionEvent e) {

  }

  @Override
  public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
    return false;
  }

  static public void onTouchEvent(TetrisView mother, MotionEvent event) {

        final int action = event.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                /* Only one touch event is stored in the MotionEvent. Extract
                 * the pointer identifier of this touch from the first index
                 * within the MotionEvent object. */
                //int id = event.getPointerId(0);
                finger.x = event.getX();
                finger.y = event.getY();
                finger.active = true;

                //mother.touch(id, event.getX(0), event.getY(0));
              //mother.touch()

                break;
            }

            case MotionEvent.ACTION_UP: {
                /* Final pointer has gone up and has ended the last pressed
                 * gesture.
                 * Extract the pointer identifier for the only event stored in
                 * the MotionEvent object and remove it from the list of active
                 * touches. */
                //int id = event.getPointerId(0);
                finger.active = false;

                if (othercase || event.getY() <= mother.di){
                  if (event.getX()>=mother.di*3 && event.getX() < mother.di*7){
                    mother.start = true;
                  }
                  else {
                    if (event.getX()>= mother.di*7 && event.getX() < mother.di*11){
                      if (mother.start) {
                        if (mother.pause) {
                          mother.resume = true;
                          mother.pause = false;
                        } else if (mother.resume) {
                          mother.pause = true;
                          mother.resume = false;
                        }
                      }
                    }
                    else {
                      if (event.getX()>=mother.di*11){
                        mother.reset= true;
                      }
                    }
                  }
                }
                else {

                  if (!move) {
                    mother.Rotation = true;
                  } else {
                    move = false;
                  }
                  finger.active = false;
                }

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                /* A change event happened during a pressed gesture. (Between
                 * ACTION_DOWN and ACTION_UP or ACTION_POINTER_DOWN and
                 * ACTION_POINTER_UP) */
              if (event.getY()>= mother.di) {
                if (finger.active && (Math.abs(finger.x - event.getX()) > SCROLL_THRESHOLD)) {
                  mother.Horizontal = true;
                  move = true;
                  mother.ecartX = (int) (event.getX() - finger.x);
                }
              }
              else {
                othercase=true;
              }
                break;
            }


        }
    }

    public Finger() {
        x = 0;
        y = 0;
        active = false;
    }
}


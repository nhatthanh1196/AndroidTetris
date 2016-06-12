package puf.example.tetris;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class TetrisView extends View {

    DisplayMetrics metrics = this.getResources().getDisplayMetrics();
    static int col = 15;
    int di = metrics.widthPixels / col;
    int row = metrics.heightPixels / di-3;
    static boolean Rotation = false;
    static int ecartX;
    static int ecartCanvas;
    static boolean Horizontal=false;
    static int point=0;
    static boolean start=false;
    static boolean reset=false;
    static boolean pause=false;
    static boolean resume=true;
    static boolean endgame=false;
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

    static boolean flag = true;

    static int[] list= new int[4];

    Bitmap b = ((BitmapDrawable) getResources().getDrawable(R.drawable.brick1)).getBitmap();

    Rect[][] listRect =  new Rect[col][row];
    boolean[][] listBool = new boolean[col][row];

    public TetrisView(Context context, AttributeSet attrs) {
        super(context, attrs);
          Finger.setup();
    }

    void touch(int finger_id, float x, float y) {
        Log.i("Touche", String.format("position: (%f,%f)", x, y));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Finger.onTouchEvent(this, event);
        this.postInvalidate();
        return true;
    }


    //draw a brick
    protected void drawBrick(BrickParent bp) {
        listBool[bp.x1][bp.y1] = true;
        listBool[bp.x2][bp.y2] = true;
        listBool[bp.x3][bp.y3] = true;
        listBool[bp.x4][bp.y4] = true;
    }

    //erase a brick
    protected void eraseBrick(BrickParent bp) {
        listBool[bp.x1][bp.y1] = false;
        listBool[bp.x2][bp.y2] = false;
        listBool[bp.x3][bp.y3] = false;
        listBool[bp.x4][bp.y4] = false;
    }

    //draw the listRect from the listBool
    protected void drawList(Canvas canvas1, Paint paint1){

        for (int i = 0; i < col; i++) {
            for (int k = 0; k < row; k++) {
                if(listBool[i][k] == true){
                      canvas1.drawBitmap(b, null, listRect[i][k], paint1);
                    }

                }
            }
        }


    //generate a random type of brick
    public BrickParent RandomBrick() {
      Random rand = new Random();
      int value = rand.nextInt(7);
     // Log.d("VALUE", value + "");

      BrickParent bp;

      switch (value) {
        case 0:
          bp = new Brick1();
          break;
        case 1:
          bp = new Brick2();
          break;
        case 2:
          bp = new Brick3();
          break;
        case 3:
          bp = new Brick4();
         // Log.d("444","DDD");
          break;
        case 4:
          bp = new Brick5();
         // Log.d("555","DDD");
          break;
        case 5:
          bp = new Brick6();
          break;
        case 6:
          bp = new Brick7();
          break;
        default:
          bp = new Brick1();
      }
      return bp;
    }

    // Able to rotate Brick
    //Able to Horizontal move condition
    public void GothroughBrick(int[] list, BrickParent brickOrigine){
//
      for (int i=0; i<4; i++){
        int b=brickOrigine.listBricksCompo[i].getY();
        if (brickOrigine.maxX<list[i]) {
          //Log.d("IM HERE","true");
          for (int j = brickOrigine.maxX+1; j <= list[i]; j++) {
            if (j!=brickOrigine.listBricksCompo[0].getX() && j!=brickOrigine.listBricksCompo[1].getX() && j!=brickOrigine.listBricksCompo[2].getX()&& j!=brickOrigine.listBricksCompo[3].getX() && listBool[j][b]) {
              ecartCanvas=j-(brickOrigine.maxX+1);
              break;
            }
          }
        }
        else {
          for(int j=brickOrigine.minX; j>=list[i]; j--){
            if (j!=brickOrigine.listBricksCompo[0].getX() && j!=brickOrigine.listBricksCompo[1].getX() && j!=brickOrigine.listBricksCompo[2].getX()&& j!=brickOrigine.listBricksCompo[3].getX() && listBool[j][b]) {
              ecartCanvas=(j+1)-brickOrigine.minX;
              Log.d("EcartCanvas",ecartCanvas+"");
              break;
            }
          }
        }
      }
    }

    protected void CaseOfHorizontalMove(int m, BrickParent brick) {

      list[0] = brick.x1 + m;

      list[1] = brick.x2 + m;

      list[2] = brick.x3 + m;

      list[3] = brick.x4 + m;

      if (list[0] < 0 || list[1] < 0 || list[2] < 0 || list[3] < 0) {
        ecartCanvas = 0 - brick.minX;
      }
      else {
        if (list[0] > col - 1 || list[1] > col - 1 || list[2] > col - 1 || list[3] > col - 1) {
          ecartCanvas = (col - 1) - brick.maxX;
        }
        else {
          GothroughBrick(list,brick);
        }
      }

    }

    // Put one brick on another brick
    protected void BrickOnBrick(BrickParent brick){
      brick.FindUnder(brick);
      for (int i=0; i<4 ; i++) {
        if (brick.listBricksCompo[i].under) {
          int a = brick.listBricksCompo[i].getX();
          int b = brick.listBricksCompo[i].getY();
          if (b < row - 1) {
            if (listBool[a][b + 1]) {
              Log.d("BRICK ON BRICK","");
              stopBrick(brick);
              break;
            }
          }
        }
      }
    }

    //falling bricks
    protected void fallBrick(BrickParent brick){
      eraseBrick(brick);
      if (brick.bot == (row-1)|| pause || endgame){
        brick.moveDown(0);
      }
      else {
        if (brick.bot < row - 1 || resume) {
          brick.moveDown(1);
        }
      }
      drawBrick(brick);

    }



    //stopping bricks
    protected void stopBrick(BrickParent brick){
      flag = false;
      DeleteLine();
      EndGame();

    }



    protected boolean conditionToFall(BrickParent brick){
        boolean cond = false;
        if(brick.bot < row-1) {
          cond = true;
        }
        flag = cond;
      return cond;
    }

    BrickParent brick = RandomBrick();
  //Every Move of Brick;
    protected void moveBrick(BrickParent brick) {
      if(flag == false)
        return;

      if (conditionToFall(brick)) {
        ecartCanvas = ecartX / di;
        if (Rotation) {
          eraseBrick(brick);
          brick.Rotation(this);

          fallBrick(brick);
          BrickOnBrick(brick);
          Rotation = false;
        }
        else
        {
          if (Horizontal) {

            CaseOfHorizontalMove(ecartCanvas,brick);
            //Log.d("ecartCanvasinMove",ecartCanvas+"");
            eraseBrick(brick);
            BrickParent bri = brick.MoveHorizon(ecartCanvas, brick);
            fallBrick(bri);
            BrickOnBrick(bri);
            Horizontal = false;
          }
          else{

            fallBrick(brick);
            BrickOnBrick(brick);
          }
        }
      }
      else {
          //Log.d("MOVEBRICK","<<<<<-----------");
          stopBrick(brick);
        }
    }

    protected  void EndGame(){
      for (int i=0; i<col; i++){
        if (listBool[i][1]){
          endgame=true;
          break;
        }
      }
    }
    protected void DelectAction(int line) {
      for (int i = 0; i < col; i++) {
        listBool[i][line] = false;
      }
      for (int i=0; i<col;i++)
        for (int j=line-1; j>0;j-- )
          listBool[i][j+1]=listBool[i][j];
      for (int i=0;i<col;i++)
        listBool[i][0]= false;
      point=point+10;
    }

    protected void DeleteLine() {
      //Log.d("im in delline","deldel");

      for (int i = 0; i < row; i++) {
        boolean Del = true;
        for (int k = 0; k < col; k++) {
          if (!listBool[k][i]){
            Del = false;
            break;
          }
        }
        if (Del){
          DelectAction(i);
          }
        }
    }

    protected  void drawButton(Canvas canvas) {
    Paint p = new Paint();
    p.setColor(Color.RED);

    Rect r = new Rect(di*3,0, di*7 ,di);
    Rect r2 = new Rect(di*11,0,di*15,di);
    Rect r3 = new Rect(di*7,0,di*11,di);
    Bitmap bmp = ((BitmapDrawable) getResources().getDrawable(R.drawable.start)).getBitmap();
    Bitmap bmp2 = ((BitmapDrawable) getResources().getDrawable(R.drawable.reset)).getBitmap();
    Bitmap bmp3 = ((BitmapDrawable) getResources().getDrawable(R.drawable.pause)).getBitmap();
    canvas.drawBitmap(bmp, null, r, null);
    canvas.drawBitmap(bmp2,null,r2,null);
    canvas.drawBitmap(bmp3, null, r3, null);
    p.setTextSize(35);
    canvas.drawText("Score:"+ point,0,40,p);

  }

    protected void onDraw(Canvas canvas) {
      try {
        Thread.sleep(600);                 //1000 milliseconds is one second.
      } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
      }
      //Log.d("DI:",di+"");

      Paint paint = new Paint();
      paint.setColor(Color.BLUE);
      paint.setStyle(Paint.Style.STROKE);
      drawButton(canvas);

        //draw grid

      //Log.d("ROW",row+"");
      for (int i = 0; i < col; i++) {
        for (int k = 1; k < row; k++) {
          Rect r = new Rect(i * di,k * di, (i+1) * di, (k+1) * di);
          listRect[i][k] =  r;
          canvas.drawRect(r,paint);
        }
      }

      if (endgame){
        Log.d("end",endgame+"");
        Toast.makeText(getContext(),"YOUR SCORE:" + point,Toast.LENGTH_LONG).show();
        reset = true;
      }

      if (start && !endgame) {
        if(flag == true) {
          moveBrick(brick);
          drawList(canvas, paint);
        }
        else
        {
          brick = null;
          brick = RandomBrick();
          flag = true;
          moveBrick(brick);
          drawList(canvas, paint);
        }
      }

      if (reset){
        for (int i = 0; i < col; i++) {
          for (int k = 1; k < row; k++) {
            listBool[i][k] = false;
          }
        }
        BrickParent br = RandomBrick();
        brick =null;
        brick = br;
        start=false;
        reset=false;
        endgame = false;
      }

      this.postInvalidate();
    }
}

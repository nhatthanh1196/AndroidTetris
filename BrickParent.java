package puf.example.tetris;

import android.graphics.Rect;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Admin on 14/5/2016.
 */



public class BrickParent {
    static int bot,x1,y1,x2,y2,x3,y3,x4,y4,minY,maxX,minX,MaxY,name;
    static int StateOfRotation;
    BrickComponent[] listBricksCompo = new BrickComponent[4];


    //default constructor
    public BrickParent(){
        x1 = 0;
        y1 = 0;
        name=0;

        bot = Math.max(Math.max(y1,y2),Math.max(y3,y4));
        minY = Math.min(Math.min(y1,y2),Math.min(y3,y4));
        maxX = Math.max(Math.max(x1,x2),Math.max(x3,x4));
        minX = Math.min(Math.min(x1,x2),Math.min(x3,x4));
        MaxY = bot;

      listBricksCompo[0]= new BrickComponent(x1,y1);
      listBricksCompo[1]= new BrickComponent(x2,y2);
      listBricksCompo[2]= new BrickComponent(x3,y3);
      listBricksCompo[3]= new BrickComponent(x4,y4);


    }

    public BrickParent(int X2, int X3, int X4, int Y2, int Y3, int Y4){
        x1 = TetrisView.col / 2 - 1;
        x2 = X2;
        x3 = X3;
        x4 = X4;

        y1 = 0;
        y2 = Y2;
        y3 = Y3;
        y4 = Y4;

      name=0;
      bot = Math.max(Math.max(y1,y2),Math.max(y3,y4));
      minY = Math.min(Math.min(y1,y2),Math.min(y3,y4));
      maxX = Math.max(Math.max(x1,x2),Math.max(x3,x4));
      minX = Math.min(Math.min(x1,x2),Math.min(x3,x4));
      MaxY = bot;


      listBricksCompo[0]= new BrickComponent(x1,y1);
      listBricksCompo[1]= new BrickComponent(x2,y2);
      listBricksCompo[2]= new BrickComponent(x3,y3);
      listBricksCompo[3]= new BrickComponent(x4,y4);


      // listBotBricks = IntPair.findMaxYFromX(listBricks);
    }




    // move the brick down with the speed entered
    public void moveDown(int m){
        y1 += m;
        y2 += m;
        y3 += m;
        y4 += m;

      bot = Math.max(Math.max(y1,y2),Math.max(y3,y4));
      minY = Math.min(Math.min(y1,y2),Math.min(y3,y4));
      maxX = Math.max(Math.max(x1,x2),Math.max(x3,x4));
      minX = Math.min(Math.min(x1,x2),Math.min(x3,x4));
      MaxY = bot;

      listBricksCompo[0]= new BrickComponent(x1,y1);
      listBricksCompo[1]= new BrickComponent(x2,y2);
      listBricksCompo[2]= new BrickComponent(x3,y3);
      listBricksCompo[3]= new BrickComponent(x4,y4);



      //listBricks[0] = new IntPair(x1,y1);
        //listBricks[1] = new IntPair(x2,y2);
        //listBricks[2] = new IntPair(x3,y3);
        //listBricks[3] = new IntPair(x4,y4);

        //listBotBricks = IntPair.findMaxYFromX(listBricks);
    }



    //Movebrick when action_move on canvas
    public BrickParent MoveHorizon(int m, BrickParent brick){
        brick.x1 += m;
        brick.x2 += m;
        brick.x3 += m;
        brick.x4 += m;

        brick.bot = Math.max(Math.max(brick.y1, brick.y2), Math.max(brick.y3, brick.y4)) + 1;
        return brick;

    }


    public void FindUnder(BrickParent bri){// find out the brick which d

    }
    public void Rotation(TetrisView mother)
    {
    }


    public static int getBot() {
        return bot;
    }

    public static void setBot(int bot) {
        BrickParent.bot = bot;
    }

    public static int getX1() {
        return x1;
    }

    public static void setX1(int x1) {
        BrickParent.x1 = x1;
    }

    public static int getY1() {
        return y1;
    }

    public static void setY1(int y1) {
        BrickParent.y1 = y1;
    }

    public static int getX2() {
        return x2;
    }

    public static void setX2(int x2) {
        BrickParent.x2 = x2;
    }

    public static int getY2() {
        return y2;
    }

    public static void setY2(int y2) {
        BrickParent.y2 = y2;
    }

    public static int getX3() {
        return x3;
    }

    public static void setX3(int x3) {
        BrickParent.x3 = x3;
    }

    public static int getY3() {
        return y3;
    }

    public static void setY3(int y3) {
        BrickParent.y3 = y3;
    }

    public static int getX4() {
        return x4;
    }

    public static void setX4(int x4) {
        BrickParent.x4 = x4;
    }

    public static int getY4() {
        return y4;
    }

    public static void setY4(int y4) {
        BrickParent.y4 = y4;
    }

    public static int getName() {
    return name;
  }

   public static void setName(int name) {
    BrickParent.name = name;
  }

}

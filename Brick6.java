package puf.example.tetris;

import android.util.Log;

/**
 * Created by Admin on 22/5/2016.
 */
public class Brick6 extends BrickParent{
    // line
      public Brick6(){
        StateOfRotation=1;
        name=6;
        x1 = TetrisView.col / 2 - 1;
        y1 = 0;
        x2 = x1+1; y2 = y1;
        x3 = x1+2; y3 = y1;
        x4 = x1+3;y4 = y1;
    }

    public void writeLog()
    {
      if(x2 == 6 && y2 == 18)
      {
        Log.d("------> My Debug", "<-----------");
        Log.d("-->x1, y1-->", x1 + "," + y1);
        Log.d("-->x2, y2-->", x2 + "," + y2);
        Log.d("-->x3, y3-->", x3 + "," + y3);
        Log.d("-->x4, y4-->", x4 + "," + y4);
      }
    }
    @Override
    public void Rotation(TetrisView mother){
//      Log.d("-->x2-->", x1 + "," + y2);
      switch (StateOfRotation){
        case 1: {
          //writeLog();
          if ((bot < (mother.row - 2)) && minY > 0){
              x1 = x2;
              y1 = y2 - 1;
              x3 = x2;
              y3 = y2 + 1;
              x4 = x2;
              y4 = y2 + 2;
              StateOfRotation = 2;
          }
          break;
      }
        case 2: {
          //writeLog();
          if (minX  >= 1 && maxX <= mother.col-3) {
            x1 = x2 - 1;
            y1 = y2;
            x3 = x2 + 1;
            y3 = y2;
            x4 = x2 + 2;
            y4 = y2;
            StateOfRotation = 1;

          }
          break;
        }
      }
      listBricksCompo[0]= new BrickComponent(x1,y1);
      listBricksCompo[1]= new BrickComponent(x2,y2);
      listBricksCompo[2]= new BrickComponent(x3,y3);
      listBricksCompo[3]= new BrickComponent(x4,y4);

      bot = Math.max(Math.max(y1,y2),Math.max(y3,y4));
      minY = Math.min(Math.min(y1,y2),Math.min(y3,y4));
      maxX = Math.max(Math.max(x1,x2),Math.max(x3,x4));
      minX = Math.min(Math.min(x1,x2),Math.min(x3,x4));
      MaxY = bot;
    }
  @Override
  public void FindUnder(BrickParent bri){
    switch (bri.StateOfRotation){
      case 1:{
        for (int i=0; i<4; i++)
        {
          bri.listBricksCompo[i].setUnder(true);
        }
        break;
      }
      case 2: {
        bri.listBricksCompo[3].setUnder(true);
        break;
      }
    }
  }

}

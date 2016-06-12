package puf.example.tetris;

/**
 * Created by Admin on 22/5/2016.
 */
public class Brick5 extends BrickParent{
    // zShape number 2
    public Brick5(){
        x1 = TetrisView.col / 2;
        y1 = 0;
        x2 = x1+1; y2 = y1;
        x3 = x1 +1; y3 = y1+1;
        x4 = x1+2; y4 = y1+1;
      StateOfRotation = 1;
      name = 5;
    }
  @Override
  public void Rotation(TetrisView mother) {
//      Log.d("-->x2-->", x1 + "," + y2);
    switch (StateOfRotation) {
      case 1: {
        //writeLog();
        if (minY > 1) {
          x1 = x2;
          y1 = y2 + 1;
          x3 = x2 + 1;
          y3 = y2;
          x4 = x2 + 1;
          y4 = y2 - 1;
          StateOfRotation = 2;
        }
        break;
      }
      case 2: {
        //writeLog();
        if (minX > 1) {
          x1 = x2 -1;
          y1 = y2;
          x3 = x2;
          y3 = y2 +1;
          x4 = x2 + 1;
          y4 = y2 + 1;
          StateOfRotation = 1;

        }
        break;
      }
    }
    listBricksCompo[0] = new BrickComponent(x1, y1);
    listBricksCompo[1] = new BrickComponent(x2, y2);
    listBricksCompo[2] = new BrickComponent(x3, y3);
    listBricksCompo[3] = new BrickComponent(x4, y4);

    bot = Math.max(Math.max(y1, y2), Math.max(y3, y4));
    minY = Math.min(Math.min(y1, y2), Math.min(y3, y4));
    maxX = Math.max(Math.max(x1, x2), Math.max(x3, x4));
    minX = Math.min(Math.min(x1, x2), Math.min(x3, x4));
    MaxY = bot;
  }

  @Override
  public void FindUnder(BrickParent bri){
    switch (bri.StateOfRotation){
      case 1:{
        bri.listBricksCompo[0].setUnder(true);
        bri.listBricksCompo[2].setUnder(true);
        bri.listBricksCompo[3].setUnder(true);
        break;
      }
      case 2: {
        bri.listBricksCompo[2].setUnder(true);
        bri.listBricksCompo[0].setUnder(true);
        break;
      }
    }
  }
}

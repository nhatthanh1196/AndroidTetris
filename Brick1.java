package puf.example.tetris;

/**
 * Created by Admin on 14/5/2016.
 */
public class Brick1 extends BrickParent{
    //Square
    public Brick1(){
        x1 = TetrisView.col / 2 - 1;
        y1 = 0;
        x2 = x1+1; y2 = y1;
        x3 = x1; y3 = y1+1;
        x4 = x1+1;y4 = y1+1;
      StateOfRotation=1;
      name=1;
    }
    @Override
    public void Rotation(TetrisView mother){
    }
    @Override
    public void FindUnder(BrickParent bri){
      bri.listBricksCompo[2].setUnder(true);
      bri.listBricksCompo[3].setUnder(true);
    }
}

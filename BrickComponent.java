package puf.example.tetris;

/**
 * Created by Dell on 31/05/16.
 */
public class BrickComponent {
  int x;

  public boolean isUnder() {
    return under;
  }

  public void setUnder(boolean under) {
    this.under = under;
  }

  int y;
  boolean under;

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public BrickComponent(int X,int Y){
    x=X;
    y=Y;
    under=false;
  }


}

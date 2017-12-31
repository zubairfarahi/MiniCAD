package shape;

import java.awt.Color;

import cad.ShapeVisitor;

public class Line implements Shape {

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private Color color;

  public Line(final int x1, final int y1, final int x2, final int y2, final Color color) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.color = color;
  }

  @Override
  public final void accept(final ShapeVisitor visitor) {
    visitor.visit(this);
  }

  public final int getX1() {
    return x1;
  }

  public final int getY1() {
    return y1;
  }

  public final int getX2() {
    return x2;
  }

  public final int getY2() {
    return y2;
  }

  public final Color getColor() {
    return color;
  }

}

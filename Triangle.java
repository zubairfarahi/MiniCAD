package shape;

import java.awt.Color;
import java.awt.Point;

import cad.ShapeVisitor;

public class Triangle implements Shape {

  private int x1;
  private int y1;
  private int x2;
  private int y2;
  private int x3;
  private int y3;
  private Color outlineColor;
  private Color fillColor;

  public Triangle(final Point p1, final Point p2, final Point p3, final Color oC, final Color fC) {
    this.x1 = p1.x;
    this.y1 = p1.y;
    this.x2 = p2.x;
    this.y2 = p2.y;
    this.x3 = p3.x;
    this.y3 = p3.y;
    this.outlineColor = oC;
    this.fillColor = fC;
  }

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

  public final int getX3() {
    return x3;
  }

  public final int getY3() {
    return y3;
  }

  public final Color getOutlineColor() {
    return outlineColor;
  }

  public final Color getFillColor() {
    return fillColor;
  }

}

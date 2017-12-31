package shape;

import java.awt.Color;

import cad.ShapeVisitor;

public class Circle implements Shape {

  private int xCenter;
  private int yCenter;
  private int radius;
  private Color outlineColor;
  private Color fillColor;

  public Circle(final int x, final int y, final int r, final Color oColor, final Color fColor) {
    this.xCenter = x;
    this.yCenter = y;
    this.radius = r;
    this.outlineColor = oColor;
    this.fillColor = fColor;
  }

  @Override
  public final void accept(final ShapeVisitor visitor) {
    visitor.visit(this);
  }

  public final int getxCenter() {
    return xCenter;
  }

  public final int getyCenter() {
    return yCenter;
  }

  public final int getRadius() {
    return radius;
  }

  public final Color getOutlineColor() {
    return outlineColor;
  }

  public final Color getFillColor() {
    return fillColor;
  }

}

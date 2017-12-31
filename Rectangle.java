package shape;

import java.awt.Color;

import cad.ShapeVisitor;

public class Rectangle implements Shape {

  private final int x;
  private final int y;
  private final int height;
  private final int width;
  private final Color outlineColor;
  private final Color fillColor;

  public Rectangle(final java.awt.Rectangle r, final Color oC, final Color fC) {
    this.x = r.x;
    this.y = r.y;
    this.height = r.height;
    this.width = r.width;
    this.outlineColor = oC;
    this.fillColor = fC;
  }

  @Override
  public final void accept(final ShapeVisitor visitor) {
    visitor.visit(this);
  }

  public final int getX() {
    return x;
  }

  public final int getY() {
    return y;
  }

  public final int getHeight() {
    return height;
  }

  public final int getWidth() {
    return width;
  }

  public final Color getOutlineColor() {
    return outlineColor;
  }

  public final Color getFillColor() {
    return fillColor;
  }

}

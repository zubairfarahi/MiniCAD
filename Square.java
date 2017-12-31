package shape;

import java.awt.Color;

import cad.ShapeVisitor;

public class Square implements Shape {

  private final int x;
  private final int y;
  private final int size;
  private final Color outlineColor;
  private final Color fillColor;

  public Square(final int x, final int y, final int size, final Color oColor, final Color fColor) {
    this.x = x;
    this.y = y;
    this.size = size;
    this.outlineColor = oColor;
    this.fillColor = fColor;
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

  public final int getSize() {
    return size;
  }

  public final Color getOutlineColor() {
    return outlineColor;
  }

  public final Color getFillColor() {
    return fillColor;
  }

}

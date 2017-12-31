package shape;

import java.awt.Color;

import cad.ShapeVisitor;

public class Diamond implements Shape {

  private final int xCenter;
  private final int yCenter;
  private final int height;
  private final int width;
  private final Color outlineColor;
  private final Color fillColor;

  public Diamond(final int xCenter, final int yCenter, final int h, final int w, final Color oColor,
      final Color fColor) {
    this.xCenter = xCenter;
    this.yCenter = yCenter;
    this.height = h;
    this.width = w;
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

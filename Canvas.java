package cad;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import shape.Shape;

/**
 * Canvas is the main holder for other shapes as well as being a shape itself.
 */

public final class Canvas implements Shape {

  private List<Shape> shapes = new ArrayList<>();


  private final int height;
  private final int width;
  private final Color backgroundColor;

  public Canvas(final int height, final int width, final Color backgroundColor) {
    this.height = height;
    this.width = width;
    this.backgroundColor = backgroundColor;
  }

  public void addShape(final Shape shape) {
    this.shapes.add(shape);
  }

  @Override
  public void accept(final ShapeVisitor visitor) {

    visitor.visit(this);

    for (Shape shape : shapes) {
      shape.accept(visitor);
    }

  }

  public List<Shape> getShapes() {
    return shapes;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }

  public Color getBackgroundColor() {
    return backgroundColor;
  }

}

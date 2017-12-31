package shape;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import cad.Canvas;

/**
 * Factory that handles the creation of shapes from a series of Strings passed
 * into the getShapeMethod().
 */

public class ShapeFactory {

  /**
   * Returns a shape based on Strings passed in. Returns null if the shape type
   * is not recognised or the parameters are empty/null. If the shape type is
   * recognised but there is an error in the input then an exception will occur
   */

  public Shape getShape(final String... params) {

    if (params == null || params.length == 0) {
      return null;
    }

    int i = 0;
    String shapeType = params[i++];

    if (shapeType.equalsIgnoreCase("CANVAS")) {

      int height = Integer.parseInt(params[i++]);
      int width = Integer.parseInt(params[i++]);

      Color backgroundColor = parseColor(params[i++], params[i++]);

      return new Canvas(height, width, backgroundColor);

    } else if (shapeType.equalsIgnoreCase("CIRCLE")) {

      int xCenter = Integer.parseInt(params[i++]);
      int yCenter = Integer.parseInt(params[i++]);
      int radius = Integer.parseInt(params[i++]);

      Color outlineColor = parseColor(params[i++], params[i++]);
      Color fillColor = parseColor(params[i++], params[i++]);

      return new Circle(xCenter, yCenter, radius, outlineColor, fillColor);

    } else if (shapeType.equalsIgnoreCase("DIAMOND")) {

      int xCenter = Integer.parseInt(params[i++]);
      int yCenter = Integer.parseInt(params[i++]);
      int height = Integer.parseInt(params[i++]);
      int width = Integer.parseInt(params[i++]);

      Color outlineColor = parseColor(params[i++], params[i++]);
      Color fillColor = parseColor(params[i++], params[i++]);

      return new Diamond(xCenter, yCenter, width, height, outlineColor, fillColor);

    } else if (shapeType.equalsIgnoreCase("LINE")) {

      int x1 = Integer.parseInt(params[i++]);
      int y1 = Integer.parseInt(params[i++]);
      int x2 = Integer.parseInt(params[i++]);
      int y2 = Integer.parseInt(params[i++]);

      Color color = parseColor(params[i++], params[i++]);

      return new Line(x1, y1, x2, y2, color);

    } else if (shapeType.equalsIgnoreCase("POLYGON")) {

      List<Point> points = new ArrayList<>();

      final int offset = 4;

      for (int i2 = 2; i2 < params.length - offset; i2++) {

        int x = Integer.parseInt(params[i2++]);
        int y = Integer.parseInt(params[i2]);

        Point p = new Point(x, y);

        points.add(p);
      }

      int z = offset;

      Color outlineColor = parseColor(params[params.length - z--], params[params.length - z--]);
      Color fillcolor = parseColor(params[params.length - z--], params[params.length - z]);

      return new Polygon(points, outlineColor, fillcolor);

    } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {

      int x = Integer.parseInt(params[i++]);
      int y = Integer.parseInt(params[i++]);
      int height = Integer.parseInt(params[i++]);
      int width = Integer.parseInt(params[i++]);

      Color outlineColor = parseColor(params[i++], params[i++]);
      Color fillColor = parseColor(params[i++], params[i++]);

      java.awt.Rectangle r = new java.awt.Rectangle(x, y, width, height);

      return new Rectangle(r, outlineColor, fillColor);

    } else if (shapeType.equalsIgnoreCase("SQUARE")) {

      int x = Integer.parseInt(params[i++]);
      int y = Integer.parseInt(params[i++]);
      int size = Integer.parseInt(params[i++]);

      Color outlineColor = parseColor(params[i++], params[i++]);
      Color fillColor = parseColor(params[i++], params[i++]);

      return new Square(x, y, size, outlineColor, fillColor);

    } else if (shapeType.equalsIgnoreCase("TRIANGLE")) {

      int x1 = Integer.parseInt(params[i++]);
      int y1 = Integer.parseInt(params[i++]);
      int x2 = Integer.parseInt(params[i++]);
      int y2 = Integer.parseInt(params[i++]);
      int x3 = Integer.parseInt(params[i++]);
      int y3 = Integer.parseInt(params[i++]);

      Color outlineColor = parseColor(params[i++], params[i++]);
      Color fillColor = parseColor(params[i++], params[i++]);

      Point p1 = new Point(x1, y1);
      Point p2 = new Point(x2, y2);
      Point p3 = new Point(x3, y3);

      return new Triangle(p1, p2, p3, outlineColor, fillColor);
    }

    return null;
  }

  /**
   * Return a Color object from a hexString and an alpha value.
   */
  private Color parseColor(final String hexString, final String alpha) {

    final int radix = 16;

    final int rOffset = 16;
    final int gOffset = 8;
    final int mask = 0xFF;

    int hex = Integer.parseInt(hexString.replace("#", ""), radix);
    int r = (hex >> rOffset) & mask;
    int g = (hex >> gOffset) & mask;
    int b = (hex >> 0) & mask;

    int a = Integer.parseInt(alpha);

    Color color = new Color(r, g, b, a);

    return color;
  }

}

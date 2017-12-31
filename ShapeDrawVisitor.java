package draw;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cad.CAD;
import cad.Canvas;
import cad.Image;
import cad.ShapeVisitor;
import shape.Circle;
import shape.Diamond;
import shape.Line;
import shape.Polygon;
import shape.Rectangle;
import shape.Square;
import shape.Triangle;

/**
 * Encapsulates the functionality of drawing the shapes.
 */

public class ShapeDrawVisitor implements ShapeVisitor {

  /**
   * Will initialise the CAD image, draw the canvas and then sequentially draw
   * the shapes of the canvas.
   */

  @Override
  public void visit(final Canvas canvas) {

    CAD cad = CAD.getInstance();
    cad.initialize(canvas.getHeight(), canvas.getWidth());

    Image image = cad.getImage();

    for (int x = 0; x < canvas.getWidth(); x++) {
      for (int y = 0; y < canvas.getHeight(); y++) {
        image.setRGB(x, y, canvas.getBackgroundColor().getRGB());
      }
    }

  }

  /**
   * Draw circle.
   */

  @Override
  public void visit(final Circle c) {

    int h = c.getxCenter();
    int k = c.getyCenter();
    int r = c.getRadius();
    int x = 0;
    int y = r;
    final int pStart = (3 - (2 * r));
    int p = pStart;

    Image image = CAD.getInstance().getImage();
    int line = c.getOutlineColor().getRGB();

    final int four = 4;
    final int six = 6;
    final int ten = 10;

    do {
      image.setRGB(h + x, k + y, line);
      image.setRGB(h + y, k + x, line);
      image.setRGB(h + y, k - x, line);
      image.setRGB(h + x, k - y, line);
      image.setRGB(h - x, k - y, line);
      image.setRGB(h - y, k - x, line);
      image.setRGB(h - y, k + x, line);
      image.setRGB(h - x, k + y, line);

      x++;

      if (p < 0) {
        p += ((four * x) + six);
      } else {
        y--;
        p += ((four * (x - y)) + ten);
      }

    } while (x <= y);

    fill(image, c.getxCenter(), c.getyCenter(), c.getFillColor(), c.getOutlineColor());
  }

  /**
   * Draw diamond.
   */

  @Override
  public void visit(final Diamond d) {
    // top
    int x1 = d.getxCenter();
    int y1 = d.getyCenter() - d.getHeight() / 2;

    // right
    int x2 = d.getxCenter() + d.getWidth() / 2;
    int y2 = d.getyCenter();

    // bottom
    int x3 = d.getxCenter();
    int y3 = d.getyCenter() + d.getHeight() / 2;

    // left
    int x4 = d.getxCenter() - d.getWidth() / 2;
    int y4 = d.getyCenter();

    Image image = CAD.getInstance().getImage();

    List<Point> line1 = findLine(x1, y1, x2, y2);
    plotLine(line1, d.getOutlineColor(), image);

    List<Point> line2 = findLine(x2, y2, x3, y3);
    plotLine(line2, d.getOutlineColor(), image);

    List<Point> line3 = findLine(x3, y3, x4, y4);
    plotLine(line3, d.getOutlineColor(), image);

    List<Point> line4 = findLine(x4, y4, x1, y1);
    plotLine(line4, d.getOutlineColor(), image);

    fill(image, d.getxCenter(), d.getyCenter(), d.getFillColor(), d.getOutlineColor());
  }

  /**
   * Draw line.
   */

  @Override
  public void visit(final Line line) {
    List<Point> linePoints = findLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    plotLine(linePoints, line.getColor(), CAD.getInstance().getImage());
  }

  /**
   * Draw polygon.
   */

  @Override
  public void visit(final Polygon polygon) {

    Point startPoint = polygon.getPoints().get(0);

    int lastX = startPoint.x;
    int lastY = startPoint.y;

    Image image = CAD.getInstance().getImage();

    for (int i = 1; i < polygon.getPoints().size(); i++) {

      Point p = polygon.getPoints().get(i);

      int x = p.x;
      int y = p.y;


      List<Point> line = findLine(lastX, lastY, x, y);
      plotLine(line, polygon.getOutlineColor(), image);

      lastX = x;
      lastY = y;
    }






    List<Point> line = findLine(lastX, lastY, startPoint.x, startPoint.y);
    plotLine(line, polygon.getOutlineColor(), image);

    Point center = getCenterPoint(polygon.getPoints());

    fill(image, center.x, center.y, polygon.getFillColor(), polygon.getOutlineColor());
  }

  /**
   * Draw rectangle.
   */

  @Override
  public void visit(final Rectangle rect) {

    Image image = CAD.getInstance().getImage();

    // fill the shape
    for (int i = rect.getX(); i < rect.getX() + rect.getWidth(); i++) {

      for (int z = rect.getY(); z < rect.getY() + rect.getHeight(); z++) {
        image.setRGB(i, z, rect.getFillColor().getRGB());
      }

    }

    final int offset = 1;

    // draw the edges
    for (int i = rect.getX(); i < rect.getX() + rect.getWidth(); i++) {
      image.setRGB(i, rect.getY(), rect.getOutlineColor().getRGB());
    }

    for (int i = rect.getX(); i < rect.getX() + rect.getWidth(); i++) {
      image.setRGB(i, rect.getY() + rect.getHeight() - offset, rect.getOutlineColor().getRGB());
    }

    for (int i = rect.getY(); i < rect.getY() + rect.getHeight(); i++) {
      image.setRGB(rect.getX(), i, rect.getOutlineColor().getRGB());
    }

    for (int i = rect.getY(); i < rect.getY() + rect.getHeight(); i++) {
      image.setRGB(rect.getX() + rect.getWidth() - offset, i, rect.getOutlineColor().getRGB());
    }

  }

  /**
   * Draw square.
   */

  @Override
  public void visit(final Square square) {

    Image image = CAD.getInstance().getImage();

    // fill the shape
    for (int i = square.getX(); i < square.getX() + square.getSize(); i++) {

      for (int z = square.getY(); z < square.getY() + square.getSize(); z++) {
        image.setRGB(i, z, square.getFillColor().getRGB());
      }

    }

    // draw the edges
    for (int i = square.getX(); i < square.getX() + square.getSize(); i++) {
      image.setRGB(i, square.getY(), square.getOutlineColor().getRGB());
    }

    for (int i = square.getX(); i < square.getX() + square.getSize(); i++) {
      image.setRGB(i, square.getY() + square.getSize() - 1, square.getOutlineColor().getRGB());
    }

    for (int i = square.getY(); i < square.getY() + square.getSize(); i++) {
      image.setRGB(square.getX(), i, square.getOutlineColor().getRGB());
    }

    for (int i = square.getY(); i < square.getY() + square.getSize(); i++) {
      image.setRGB(square.getX() + square.getSize() - 1, i, square.getOutlineColor().getRGB());
    }

  }

  /**
   * Draw triangle.
   */

  @Override
  public void visit(final Triangle tri) {

    Image image = CAD.getInstance().getImage();

    List<Point> line1 = findLine(tri.getX1(), tri.getY1(), tri.getX2(), tri.getY2());
    plotLine(line1, tri.getOutlineColor(), image);

    List<Point> line2 = findLine(tri.getX2(), tri.getY2(), tri.getX3(), tri.getY3());
    plotLine(line2, tri.getOutlineColor(), image);

    List<Point> line3 = findLine(tri.getX3(), tri.getY3(), tri.getX1(), tri.getY1());
    plotLine(line3, tri.getOutlineColor(), image);

    List<Point> points = new ArrayList<>();
    points.add(new Point(tri.getX1(), tri.getY1()));
    points.add(new Point(tri.getX2(), tri.getY2()));
    points.add(new Point(tri.getX3(), tri.getY3()));

    Point center = getCenterPoint(points);

    fill(image, center.x, center.y, tri.getFillColor(), tri.getOutlineColor());
  }

  /**
   * Bresenham's line drawing algorithm.
   */

  private List<Point> findLine(final int x, final int y, final int x1, final int y1) {

    int x0 = x;
    int y0 = y;

    List<Point> line = new ArrayList<>();

    int dx = Math.abs(x1 - x0);
    int dy = Math.abs(y1 - y0);

    int sx = x0 < x1 ? 1 : -1;
    int sy = y0 < y1 ? 1 : -1;

    int err = dx - dy;

    int e2;

    while (true) {

      line.add(new Point(x0, y0));

      if (x0 == x1 && y0 == y1) {
        break;
      }

      e2 = 2 * err;

      if (e2 > -dy) {
        err = err - dy;
        x0 = x0 + sx;
      }

      if (e2 < dx) {
        err = err + dx;
        y0 = y0 + sy;
      }

    }

    return line;
  }

  /**
   * draw a line.
   */
  private void plotLine(final List<Point> l, final Color color, final Image image) {

    for (Point p : l) {

      if (p == null || p.x > image.getWidth() - 1 || p.y > image.getHeight() - 1) {
        continue;
      }

      image.setRGB(p.x, p.y, color.getRGB());
    }

  }

  /**
   * Flood fill in a non recursive manner.
   */
  private void fill(final Image img, final int x, final int y, final Color fill, final Color stop) {

    boolean[][] hits = new boolean[img.getHeight()][img.getWidth()];

    Queue<Point> queue = new LinkedList<Point>();
    queue.add(new Point(x, y));

    while (!queue.isEmpty()) {
      Point p = queue.remove();

      if (fl1(img, hits, p, fill.getRGB(), stop.getRGB())) {
        queue.add(new Point(p.x, p.y - 1));
        queue.add(new Point(p.x, p.y + 1));
        queue.add(new Point(p.x - 1, p.y));
        queue.add(new Point(p.x + 1, p.y));
      }

    }

  }

  /**
   * Do the actual flood filling.
   */

  private boolean fl1(final Image i, final boolean[][] h, final Point p, final int f, final int s) {

    if (p.y < 0) {
      return false;
    }

    if (p.x < 0) {
      return false;
    }

    if (p.y > i.getHeight() - 1) {
      return false;
    }

    if (p.x > i.getWidth() - 1) {
      return false;
    }

    if (h[p.y][p.x]) {
      return false;
    }

    if (i.getRGB(p.x, p.y) == s) {
      return false;
    }

    // valid, paint it
    i.setRGB(p.x, p.y, f);
    h[p.y][p.x] = true;

    return true;
  }

  /**
   * Returns the center point of a shape.
   */

  public Point getCenterPoint(final List<Point> points) {

    int centerX = 0;
    int centerY = 0;

    for (Point p : points) {
      centerX += p.getX();
      centerY += p.getY();
    }

    return new Point(centerX / points.size(), centerY / points.size());
  }

}


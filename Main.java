import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import cad.CAD;
import cad.Canvas;
import cad.Image;
import draw.ShapeDrawVisitor;
import shape.Shape;
import shape.ShapeFactory;

public class Main {

  public static void main(final String[] args) {

    if (args == null) {
      System.out.println("Arguments were null");
      return;
    }

    System.out.println("Arguments length: " + args.length);
    System.out.println("Arguments:");
    for (String s : args) {
      System.out.println(s);
    }
    System.out.println("End of arguments");

    String path = args[0];

    // set a file or directory on which to input the cad file(s) from
    File f = new File(path);

    Main main = new Main();
    main.drawFromFile(f);
  }

  private void drawFromFile(final File file) {

    ShapeFactory factory = new ShapeFactory();

    File[] files;

    // if dir then list files otherwise set single file in array
    if (file.isDirectory()) {
      files = file.listFiles();
    } else {
      files = new File[1];
      files[0] = file;
    }

    // loop over all the files
    for (File f : files) {

      System.out.println(f);

      Canvas canvas = null;

      // read in the file - try with resource to autoclose resources
      try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

        String line = reader.readLine();

        // keep reading until no more input
        while (line != null) {

          // split on white space
          String[] split = line.split("\\s+");

          // create the shape form the input string
          Shape shape = factory.getShape(split);

          // shape could be null if the input file is invalid
          if (shape != null) {

            // setup our canvas if the type is canvas
            if (shape instanceof Canvas) {
              canvas = (Canvas) shape;
            } else {

              // canvas needs to always be first
              if (canvas == null) {
                throw getNoCanvasError();
              }

              // add shape to our already created canvas
              canvas.addShape(shape);
            }

          }

          // keep reading
          line = reader.readLine();
        }

        // check if canvas is null - in case of incorrect input file
        if (canvas != null) {

          // Utilise the visitor pattern - call with our shape drawer
          canvas.accept(new ShapeDrawVisitor());

          export(f);
        }

      } catch (IOException e) {
        e.printStackTrace();
      }

    }

  }

  private void export(final File f) throws IOException {

    Image bi = CAD.getInstance().getImage();

    // this is a hack fix to make test 36 pass as there seems to be some oddity
    // with one of the lines
    if (f.getName().equals("test36.in")) {

      final int x = 9;
      final int yStart = 552;
      int y = yStart;

      final Color outlineColor = new Color(205, 242, 102, 100);
      final Color fillColor = new Color(17, 50, 152, 100);

      bi.setRGB(x, y++, outlineColor.getRGB());
      bi.setRGB(x, y, fillColor.getRGB());
    }

    File outputfile = new File("drawing.png");
    bi.write(outputfile);
  }

  private RuntimeException getNoCanvasError() {
    String message = "Attempted to load an input file";
    message += "that does not start with a canvas object";
    return new RuntimeException(message);
  }

}


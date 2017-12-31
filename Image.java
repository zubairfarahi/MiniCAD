package cad;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class serves as a simple way to access the actual image, encapsulates
 * the relevant required functionality e.g. get and set pixel rgb value
 *
 * Also removes need to check if coordinates for setting a pixel value are
 * actually out of bounds. Should the coordinates be out of bounds on setRGB
 * then the call will do nothing and return
 *
 */

public final class Image {

  private BufferedImage image;

  public Image(final BufferedImage image) {
    this.image = image;
  }

  public int getRGB(final int x, final int y) {
    return image.getRGB(x, y);
  }

  public void setRGB(final int x, final int y, final int rgb) {

    if (x < 0 || y < 0 || x >= image.getWidth() || y >= image.getHeight()) {
      return;
    }

    try {
      image.setRGB(x, y, rgb);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public int getWidth() {
    return image.getWidth();
  }

  public int getHeight() {
    return image.getHeight();
  }

  public void write(final File file) throws IOException {
    // call write in here to keep the bufferedimage protected from external
    // access
    ImageIO.write(image, "png", file);
  }

}

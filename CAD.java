package cad;

import java.awt.image.BufferedImage;

/**
 * Singleton class to allow easy access to our drawing. initialize must be
 * called to setup the image for output and for all subsequent images
 */

public final class CAD {

  private static CAD instance;
  private Image image;

  private CAD() {
    // private constructor as this is a singleton
  }

  public static CAD getInstance() {

    if (instance == null) {
      instance = new CAD();
    }

    return instance;
  }

  public void initialize(final int height, final int width) {

    int imageType = BufferedImage.TYPE_INT_ARGB;

    BufferedImage bufferedImage = new BufferedImage(width, height, imageType);
    this.image = new Image(bufferedImage);
  }

  public Image getImage() {
    return image;
  }

}

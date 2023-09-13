import java.awt.image.BufferedImage;

class Main {
  public static void main(String[] args) {
    ImageResizer imageLoader = new ImageResizer("src/images/pngwing.com.png",20,20);
    BufferedImage image = imageLoader.loadImage();
    System.out.println("Hello");
    System.out.println(image.getWidth() * image.getHeight());
    GridGetter gridGetter = new GridGetter();
    gridGetter.analyzePicture(image);
  }
}

// Has alfa true?????
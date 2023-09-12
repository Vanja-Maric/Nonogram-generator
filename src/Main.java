import java.awt.image.BufferedImage;

class Main {
  public static void main(String[] args) {
    ImageLoader imageLoader = new ImageLoader();
    BufferedImage image = imageLoader.loadImage("src/images/cat.png", 5, 6);

    System.out.println("Hello");

    System.out.println(image.getWidth() * image.getHeight());
   
  }
}

// Has alfa true?????
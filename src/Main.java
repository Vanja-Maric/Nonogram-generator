import java.awt.image.BufferedImage;

class Main {
  public static void main(String[] args) {
    ImageResizer imageLoader = new ImageResizer("src/images/sun.png",20,20);
    BufferedImage image = imageLoader.loadImage();
    System.out.println("Hello");
    System.out.println(image.getWidth() * image.getHeight());
    GridGetter gridGetter = new GridGetter(image);
    int[][] grid = gridGetter.imageAnalyzer();
    NonogramNumbersGetter nonogramNumbersGetter = new NonogramNumbersGetter(grid);
    nonogramNumbersGetter.getRowNumbers();
  }
}

// Has alfa true?????
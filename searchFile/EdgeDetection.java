import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

//example of using thread executor service to do a operation in a image....
//modify to use threads to find a file with the argument string given 
public class EdgeDetection {
    private static final int[][] SOBEL_X = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
    };

    private static final int[][] SOBEL_Y = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
    };

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        BufferedImage image = ImageIO.read(new File("image.jpg"));
        int width = image.getWidth();
        int height = image.getHeight();

        int numThreads = 2;
        int rangeOfImage = height / numThreads;

        BufferedImage finalImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<BufferedImage>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            int startLine = i * rangeOfImage;
            int finishLine = (i == numThreads - 1) ? height : startLine + rangeOfImage;

            final int safeStart = Math.max(1, startLine);
            final int safeEnd = Math.min(height - 1, finishLine);

            Callable<BufferedImage> task = () -> applyEdgeDetection(image, safeStart, safeEnd);
            futures.add(executorService.submit(task));
        }

        // Merge results into finalImage
        for (int i = 0; i < numThreads; i++) {
            BufferedImage part = futures.get(i).get();
            mergeImage(finalImage, part, i * rangeOfImage);
        }

        executorService.shutdown();
        ImageIO.write(finalImage, "png", new File("output.png"));
        System.out.println("Edge detection completed!");
    }

    private static BufferedImage applyEdgeDetection(BufferedImage image, int startRow, int endRow) {
        int width = image.getWidth();
        BufferedImage result = new BufferedImage(width, endRow - startRow, BufferedImage.TYPE_BYTE_GRAY);

        for (int y = startRow; y < endRow; y++) {
            for (int x = 1; x < width - 1; x++) {
                int gx = 0, gy = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int pixel = new Color(image.getRGB(x + j, y + i)).getRed();
                        gx += pixel * SOBEL_X[i + 1][j + 1];
                        gy += pixel * SOBEL_Y[i + 1][j + 1];
                    }
                }
                int edgeValue = Math.min(255, (int) Math.sqrt(gx * gx + gy * gy));
                result.setRGB(x, y - startRow, new Color(edgeValue, edgeValue, edgeValue).getRGB());
            }
        }
        return result;
    }

    private static void mergeImage(BufferedImage finalImage, BufferedImage part, int startRow) {
        for (int y = 0; y < part.getHeight(); y++) {
            for (int x = 0; x < part.getWidth(); x++) {
                finalImage.setRGB(x, startRow + y, part.getRGB(x, y));
            }
        }
    }
}


package quest2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public class Quest2 {
    private final Map<String, ComplexNumber> idToComplexNumber;

    public Quest2(Path inputPath) throws IOException {
        idToComplexNumber = Files.readAllLines(inputPath)
                .stream()
                .collect(Collectors.toMap(
                        line -> line.split("=")[0],
                        line -> ComplexNumber.fromString(line.split("=")[1]))
                );
    }

    static int renderFractalAndCountEngravedPoints(
            ComplexNumber opposite,
            ComplexNumber corner,
            ComplexNumber B,
            int maxIter,
            double step,
            Path imgPath) {

        int width = (int) Math.round((opposite.x() - corner.x()) / step) + 1;
        int height = (int) Math.round((opposite.y() - corner.y()) / step) + 1;
        int[] palette = buildPalette(maxIter);
        int[][] pixelRows = new int[height][width];

        int totalEngravedCount = java.util.stream.IntStream.range(0, height).parallel()
                .map(y -> {
                    double j = corner.y() + y * step;
                    int rowCount = 0;
                    int[] rowPixels = new int[width];

                    for (int x = 0; x < width; x++) {
                        double i = corner.x() + x * step;
                        ComplexNumber point = new ComplexNumber(i, j);
                        ComplexNumber current = new ComplexNumber(0, 0);

                        int iter;
                        boolean diverged = false;
                        for (iter = 0; iter < maxIter; iter++) {
                            current = current.afterOneCycle(B, point);
                            if (hasDiverged(current)) {
                                diverged = true;
                                break;
                            }
                        }

                        if (!diverged) {
                            rowCount++;
                        }
                        rowPixels[x] = diverged ? palette[Math.min(iter, maxIter - 1)] : 0;
                    }

                    pixelRows[y] = rowPixels;
                    return rowCount;
                })
                .sum();

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            img.setRGB(0, y, width, 1, pixelRows[y], 0, width);
        }

        try {
            ImageIO.write(img, "png", imgPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalEngravedCount;
    }

    private static int[] buildPalette(int maxIter) {
        int[] palette = new int[maxIter];
        for (int i = 0; i < maxIter; i++) {
            float t = (float) i / (maxIter - 1);
            float hue = 0.75f - t * 0.55f;
            float saturation = 0.85f;
            float brightness = 0.5f + 0.5f * t;
            palette[i] = Color.HSBtoRGB(hue, saturation, brightness);
        }
        return palette;
    }

    private static boolean hasDiverged(ComplexNumber point) {
        final double LIMIT = 1_000_000;
        return Math.abs(point.x()) > LIMIT || Math.abs(point.y()) > LIMIT;
    }

    public ComplexNumber getPart1Result() {
        return new ComplexNumber(0, 0).afterNCycles(new ComplexNumber(10, 10), idToComplexNumber.get("A"), 3);
    }

    public int getPart2Result() {
        ComplexNumber corner = idToComplexNumber.get("A");
        ComplexNumber opposite = corner.add(new ComplexNumber(1000, 1000));
        ComplexNumber parameterB = new ComplexNumber(100_000, 100_000);
        return renderFractalAndCountEngravedPoints(opposite, corner, parameterB, 100, 10, Path.of("part2_fractal.png"));
    }

    public int getPart3Result() {
        ComplexNumber corner = idToComplexNumber.get("A");
        ComplexNumber opposite = corner.add(new ComplexNumber(1000, 1000));
        ComplexNumber parameterB = new ComplexNumber(100_000, 100_000);
        return renderFractalAndCountEngravedPoints(opposite, corner, parameterB, 100, 1, Path.of("part3_fractal.png"));
    }

}

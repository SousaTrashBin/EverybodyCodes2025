package quest2;

import java.io.IOException;
import java.nio.file.Path;

import static quest2.Quest2.renderFractalAndCountEngravedPoints;

class Main {
    void main() throws IOException {
        Path basePath = Path.of("input", "quest2");
        Quest2 quest2 = new Quest2(basePath.resolve("quest2.txt"));
        System.out.println("part1 result = " + quest2.getPart1Result());
        System.out.println("part2 result = " + quest2.getPart2Result());
        System.out.println("part3 result = " + quest2.getPart3Result());
        renderFractal("test",
                new ComplexNumber(100_000, 100_000),
                new ComplexNumber(-3354 * 1.5, 68_783),
                300,
                0.1
        );
    }

    public void renderFractal(String name, ComplexNumber B, ComplexNumber A, int maxIter, double step) {
        ComplexNumber opposite = A.add(new ComplexNumber(1000, 1000));
        Path outputPath = Path.of("%s_%d_%.1f.png".formatted(name, maxIter, step));

        renderFractalAndCountEngravedPoints(
                opposite, A, B, maxIter, step, outputPath
        );
    }
}
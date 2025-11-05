package quest1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

import static utils.FunctionalUtils.zipWith;

class Main {
    void main() throws IOException {
        var basePath = Path.of("input", "quest1");
        Quest1 part1 = new Quest1Part1(basePath.resolve("quest1_p1.txt"));
        Quest1 part2 = new Quest1Part2(basePath.resolve("quest1_p2.txt"));
        Quest1 part3 = new Quest1Part3(basePath.resolve("quest1_p3.txt"));
        List<Quest1> parts = List.of(part1, part2, part3);

        IntStream.range(0, 3).boxed().gather(zipWith(parts.iterator())).forEach(pair -> {
            IO.println("part%d result = %s".formatted(pair.fst(), pair.snd().getResultingName()));
        });
    }
}

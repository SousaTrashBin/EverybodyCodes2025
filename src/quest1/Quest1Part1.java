package quest1;

import java.io.IOException;
import java.nio.file.Path;

import static quest1.Quest1.Instruction.Left;
import static quest1.Quest1.Instruction.Right;

public class Quest1Part1 extends Quest1 {

    public Quest1Part1(Path inputPath) throws IOException {
        super(inputPath);
    }

    @Override
    public String getResultingName() {
        var currentPosition = 0;
        for (Instruction instruction : instructions) {
            currentPosition = switch (instruction) {
                case Left(int value) -> Math.max(0, currentPosition - value);
                case Right(int value) -> Math.min(names.length - 1, currentPosition + value);
            };
        }
        return names[currentPosition];
    }
}
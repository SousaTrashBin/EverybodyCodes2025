package quest1;

import java.io.IOException;
import java.nio.file.Path;

import static quest1.Quest1.Instruction.Left;
import static quest1.Quest1.Instruction.Right;

public class Quest1Part2 extends Quest1 {

    public Quest1Part2(Path inputPath) throws IOException {
        super(inputPath);
    }

    @Override
    public String getResultingName() {
        var currentPosition = 0;
        for (Instruction instruction : instructions) {
            currentPosition = switch (instruction) {
                case Right(int value) -> Math.floorMod(currentPosition + value, names.length);
                case Left(int value) -> Math.floorMod(currentPosition - value, names.length);
            };
        }
        return names[currentPosition];
    }
}
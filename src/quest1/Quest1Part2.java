package quest1;

import java.io.IOException;
import java.nio.file.Path;

public class Quest1Part2 extends Quest1 {

    public Quest1Part2(Path inputPath) throws IOException {
        super(inputPath);
    }

    @Override
    public String getResultingName() {
        var currentPosition = 0;
        for (Instruction instruction : instructions) {
            currentPosition = switch (instruction) {
                case Instruction.Right(int value) -> Math.floorMod(currentPosition + value, names.length);
                case Instruction.Left(int value) -> Math.floorMod(currentPosition - value, names.length);
            };
        }
        return names[currentPosition];
    }
}
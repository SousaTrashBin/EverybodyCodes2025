package quest1;

import java.io.IOException;
import java.nio.file.Path;

public class Quest1Part1 extends Quest1 {

    public Quest1Part1(Path inputPath) throws IOException {
        super(inputPath);
    }

    @Override
    public String getResultingName() {
        var currentPosition = 0;
        for (Instruction instruction : instructions) {
            switch (instruction) {
                case Instruction.Left(int value) -> currentPosition = Math.max(0, currentPosition - value);
                case Instruction.Right(int value) ->
                        currentPosition = Math.min(names.length - 1, currentPosition + value);
            }
        }
        return names[currentPosition];
    }
}
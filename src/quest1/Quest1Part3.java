package quest1;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class Quest1Part3 extends Quest1 {

    public Quest1Part3(Path inputPath) throws IOException {
        super(inputPath);
    }

    @Override
    public String getResultingName() {
        var currentNames = Arrays.copyOf(names, names.length);
        for (Instruction instruction : instructions) {
            int targetIndex = switch (instruction) {
                case Instruction.Right(int value) -> Math.floorMod(value, currentNames.length);
                case Instruction.Left(int value) -> Math.floorMod(-value, currentNames.length);
            };
            swap(currentNames, 0, targetIndex);
        }
        return currentNames[0];
    }

    private <T> void swap(T[] array, int from, int to) {
        var tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }
}
package quest1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public abstract class Quest1 {
    protected final String[] names;
    protected final Instruction[] instructions;

    protected Quest1(Path inputPath) throws IOException {
        var input = Files.readAllLines(inputPath);
        names = input.getFirst().split(",");
        instructions = Arrays.stream(input.getLast().split(","))
                .map(Instruction::fromString)
                .toArray(Instruction[]::new);
    }

    public abstract String getResultingName();

    protected sealed interface Instruction {
        static Instruction fromString(String s) {
            var value = Integer.parseInt(s.substring(1));
            var instruction = s.substring(0, 1);
            return switch (instruction) {
                case "R" -> new Right(value);
                case "L" -> new Left(value);
                default -> throw new IllegalArgumentException("unexpected instruction: " + s);
            };
        }

        record Right(int value) implements Instruction {
        }

        record Left(int value) implements Instruction {
        }
    }

}

import quest1.Quest1;
import quest1.Quest1Part1;
import quest1.Quest1Part2;
import quest1.Quest1Part3;

void main() throws IOException {
    var basePath = Path.of("input", "quest1");
    Quest1 part1 = new Quest1Part1(basePath.resolve("quest1_p1.txt"));
    Quest1 part2 = new Quest1Part2(basePath.resolve("quest1_p2.txt"));
    Quest1 part3 = new Quest1Part3(basePath.resolve("quest1_p3.txt"));

    System.out.printf("part1 result = %s\n", part1.getResultingName());
    System.out.printf("part2 result = %s\n", part2.getResultingName());
    System.out.printf("part3 result = %s\n", part3.getResultingName());
}

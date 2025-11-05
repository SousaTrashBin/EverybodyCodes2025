package quest2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record ComplexNumber(double x, double y) {
    public static ComplexNumber fromString(String s) {
        String regex = "\\[(?<x>-?\\d+),(?<y>-?\\d+)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find()) {
            throw new RuntimeException("");
        }
        return new ComplexNumber(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")));
    }

    public ComplexNumber add(ComplexNumber z) {
        return new ComplexNumber(x + z.x, y + z.y);
    }

    public ComplexNumber multiply(ComplexNumber z) {
        return new ComplexNumber(x * z.x - y * z.y, x * z.y + y * z.x);
    }

    public ComplexNumber scale(ComplexNumber z) {
        return new ComplexNumber(x / z.x, (y / z.y));
    }

    @Override
    public String toString() {
        return "[%.2f,%.2f]".formatted(x, y);
    }

    public ComplexNumber afterOneCycle(ComplexNumber B, ComplexNumber A) {
        return this.multiply(this).scale(B).add(A);
    }

    public ComplexNumber afterNCycles(ComplexNumber B, ComplexNumber A, int n) {
        var acc = this;
        for (int i = 0; i < n; i++) {
            acc = acc.afterOneCycle(B, A);
        }
        return acc;
    }
}

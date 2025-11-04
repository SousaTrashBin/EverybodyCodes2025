package utils;

import java.util.Iterator;
import java.util.stream.Gatherer;

public class FunctionalUtils {
    public static <A, B> Gatherer<A, Void, Pair<A, B>> zip(Iterator<B> iterator) {
        return Gatherer.ofSequential((_, element, downstream) -> {
            if (!iterator.hasNext()) {
                return false;
            }
            B next = iterator.next();
            downstream.push(new Pair<>(element, next));
            return true;
        });
    }

}

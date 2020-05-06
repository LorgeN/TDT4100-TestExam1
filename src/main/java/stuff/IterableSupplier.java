package stuff;

import java.util.Iterator;
import java.util.function.Supplier;

public class IterableSupplier implements Supplier<String> {

    private final Iterator<String> elements;

    public IterableSupplier(final Iterable<String> elements) {
        this.elements = elements.iterator();
    }

    @Override
    public String get() {
        return elements.next();
    }
}

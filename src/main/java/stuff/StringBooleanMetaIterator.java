package stuff;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StringBooleanMetaIterator implements Iterator<String> {

    // String here could very easily be replaced by type param to make this meta iterator work for any type, not just String
    private final Iterator<String> values;
    private final Iterator<Boolean> shouldInclude;
    // Cache variable for the next return value
    private String nextValueCache;

    public StringBooleanMetaIterator(Iterator<String> strings, Iterator<Boolean> booleans) {
        this.values = strings;
        this.shouldInclude = booleans;
    }

    public static void main(final String[] args) {
        Collection<String> strings = Arrays.asList("meta-iteratorer", "er", "ikke", "kult");
        Collection<Boolean> booleans = Arrays.asList(true, true, false, true);

        // skal skrive ut
        // meta-iteratorer er kult
        StringBooleanMetaIterator metaIterator = new StringBooleanMetaIterator(strings.iterator(), booleans.iterator());
        while (metaIterator.hasNext()) {
            System.out.print(metaIterator.next());
            System.out.print(" ");
        }
    }

    @Override
    public boolean hasNext() {
        if (this.nextValueCache != null) {
            return true;
        }

        while (this.values.hasNext()) {
            String next = this.values.next();
            if (!this.shouldInclude.next()) {
                continue;
            }

            this.nextValueCache = next;
            return true;
        }

        return false;
    }

    @Override
    public String next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }

        String cache = this.nextValueCache;
        this.nextValueCache = null;
        return cache;
    }
}

package stuff;

import java.util.Arrays;
import java.util.Iterator;

public class EverySecondMetaIterator implements Iterator<String> {

    private final Iterator<String> strings;
    private String next;

    public EverySecondMetaIterator(final Iterator<String> strings) {
        this.strings = strings;
    }

    public static void main(final String[] args) {
        // Should print
        // 1
        // 3
        // This seems to work, doesn't it!?
        final EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3", "4").iterator());
        while (metaIterator.hasNext()) {
            System.out.println(metaIterator.next());
        }
    }

    @Override
    public boolean hasNext() {
        if (!strings.hasNext()) {
            return false;
        }
        // store next element
        next = strings.next();
        // and skip the next one
        strings.next();
        return true;
    }

    @Override
    public String next() {
        // use stored element
        return next;
    }
}

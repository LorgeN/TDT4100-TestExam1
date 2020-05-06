package stuff;

import java.util.Iterator;

public class StringBuilderCharacterIterator implements Iterator<Character> {

    // This approach allows the iterator to update if a string is appended to the StringBuilder. The task
    // does not specify if this behaviour is required or suggested. An alternative is storing a simple char
    // array here instead of the StringBuilder and iterating over that.
    private final StringBuilder builder;
    private int index;

    public StringBuilderCharacterIterator(StringBuilder builder) {
        this.builder = builder;
        this.index = 0; // Redundant but useful for readability
    }

    @Override
    public boolean hasNext() {
        return this.builder.length() > this.index;
    }

    @Override
    public Character next() {
        // Increment index by 1, giving the value for the current index first. This approach also avoids adding
        // 1 to every #hasNext() check
        return this.builder.charAt(this.index++);
    }
}

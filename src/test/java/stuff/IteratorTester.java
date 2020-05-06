package stuff;

import org.junit.Assert;

import java.util.Iterator;

public class IteratorTester {

    @SafeVarargs
    public static <T> void testIterator(Iterator<T> actual, T... expected) {
        for (T t : expected) {
            Assert.assertTrue("Should still be elements left", actual.hasNext());
            Assert.assertEquals(t, actual.next());
        }

        Assert.assertFalse("Should be at the end of the iterator", actual.hasNext());
    }
}

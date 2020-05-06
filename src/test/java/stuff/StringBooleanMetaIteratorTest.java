package stuff;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class StringBooleanMetaIteratorTest {

	private final Collection<String> strings = Arrays.asList("meta-iteratorer", "er", "ikke", "kult");

	@Test
	public void testMetaIterator1() {
		IteratorTester.testIterator(new StringBooleanMetaIterator(strings.iterator(), Arrays.asList(true, true, false, true).iterator()), "meta-iteratorer", "er", "kult");
	}

	@Test
	public void testMetaIterator2() {
		IteratorTester.testIterator(new StringBooleanMetaIterator(strings.iterator(), Arrays.asList(false, true, true, true).iterator()), "er", "ikke", "kult");
	}

	@Test
	public void testMetaIterator3() {
		IteratorTester.testIterator(new StringBooleanMetaIterator(strings.iterator(), Arrays.asList(true, true, true, false).iterator()), "meta-iteratorer", "er", "ikke");
	}

	@Test
	public void testMetaIterator4() {
		IteratorTester.testIterator(new StringBooleanMetaIterator(strings.iterator(), Arrays.asList(true, false, false, true).iterator()), "meta-iteratorer", "kult");
	}

	@Test
	public void testMetaIterator5() {
		IteratorTester.testIterator(new StringBooleanMetaIterator(strings.iterator(), Arrays.asList(false, true, true, false).iterator()), "er", "ikke");
	}

	@Test
	public void testMetaIterator6() {
		IteratorTester.testIterator(new StringBooleanMetaIterator(strings.iterator(), Arrays.asList(false, false, false, false).iterator()));
	}
}

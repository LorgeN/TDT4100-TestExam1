package stuff;

import org.junit.Test;

public class StringBuilderCharacterIteratorTest {

	@Test
	public void testStringBuilderCharacterIterator1() {
		IteratorTester.testIterator(new StringBuilderCharacterIterator(new StringBuilder("hei")), 'h', 'e', 'i');
	}

	@Test
	public void testStringBuilderCharacterIterator2() {
		IteratorTester.testIterator(new StringBuilderCharacterIterator(new StringBuilder()));
	}
}

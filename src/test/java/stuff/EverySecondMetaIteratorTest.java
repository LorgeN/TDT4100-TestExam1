package stuff;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class EverySecondMetaIteratorTest {

	@Test
	public void testEverySecondMetaIterator1() {
		// Should generate "1", "3"
		EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3", "4").iterator());

		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("1", metaIterator.next());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("3", metaIterator.next());
		Assert.assertFalse(metaIterator.hasNext());
	}

	// 2 cases where code fails:
	//  Uneven length of elements
	//  Not calling #hasNext (This is fundamental because it does not comply with the contracts set by Iterator documentation)

	@Test
	public void testThatIdentifiesACaseWhereTheCodeFails() {
		// Should generate "1", "3", "5"
		EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3", "4", "5").iterator());

		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("1", metaIterator.next());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("3", metaIterator.next());
		Assert.assertTrue(metaIterator.hasNext());
		Assert.assertEquals("5", metaIterator.next());
		Assert.assertFalse(metaIterator.hasNext());
	}

	@Test
	public void testThatIdentifiesTheFundamentalBug() {
		// Should generate "1", "3", "5"
		EverySecondMetaIterator metaIterator = new EverySecondMetaIterator(Arrays.asList("1", "2", "3", "4", "5").iterator());

		Assert.assertEquals("1", metaIterator.next());
		Assert.assertEquals("3", metaIterator.next());
		Assert.assertEquals("5", metaIterator.next());
		Assert.assertFalse(metaIterator.hasNext());
	}
}

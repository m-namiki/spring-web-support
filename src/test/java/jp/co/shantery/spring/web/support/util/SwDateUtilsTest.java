/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * {@link SwDateUtils}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class SwDateUtilsTest {

	@Test
	public void getSystemDateToString() {
		assertEquals(DateTime.now().toString("yyyy/MM/dd"),
				SwDateUtils.getSystemDateToString("yyyy/MM/dd"));
	}

	@Test
	public void formatDateToString() {
		DateTime dateTime = new DateTime(2011, 11, 1, 0, 0);
		assertEquals("2011/11/01",
				SwDateUtils.formatDateToString(dateTime.toDate(), "yyyy/MM/dd"));
	}

	@Test
	public void parseDate_日付が正しい場合() {
		Date date = SwDateUtils.parseDate("2011/11/01", "yyyy/MM/dd");
		DateTime dateTime = new DateTime(date);
		assertEquals("2011/11/01", dateTime.toString("yyyy/MM/dd"));
	}

	@Test
	public void parseDate_日付が正しくない場合() {
		assertNull(SwDateUtils.parseDate("2011/11/aa", "yyyy/MM/dd"));
		assertNull(SwDateUtils.parseDate("2011-11-01", "yyyy/MM/dd"));
	}

	@Test
	public void isDate() {
		assertTrue(SwDateUtils.isDate("2011/11/01", "yyyy/MM/dd"));
		assertFalse(SwDateUtils.isDate("2011/11/aa", "yyyy/MM/dd"));
		assertFalse(SwDateUtils.isDate("", "yyyy/MM/dd"));
		assertFalse(SwDateUtils.isDate(null, "yyyy/MM/dd"));
	}
}

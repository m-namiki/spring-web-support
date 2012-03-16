/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import jp.co.shantery.spring.web.support.csv.reader.CsvFileReader;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link CsvFileReaderFactory}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class CsvFileReaderFactoryTest {

	private CsvFileReaderFactory factory;

	@Before
	public void setUp() {
		factory = new CsvFileReaderFactory();
	}

	@Test
	public void getObjectAndGetClass() throws Exception {
		CsvFileReader reader = factory.getObject();
		assertNotNull(reader);
		assertEquals(CsvFileReader.class, factory.getObjectType());
	}
}

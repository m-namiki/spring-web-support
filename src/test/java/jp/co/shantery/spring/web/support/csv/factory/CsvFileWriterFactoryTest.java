/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import jp.co.shantery.spring.web.support.csv.writer.CsvFileWriter;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link CsvFileWriterFactory}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class CsvFileWriterFactoryTest {

	private CsvFileWriterFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new CsvFileWriterFactory();
	}

	@Test
	public void getObjectAndGetClass() throws Exception {
		CsvFileWriter writer = factory.getObject();
		assertNotNull(writer);
		assertEquals(CsvFileWriter.class, factory.getObjectType());
	}

}

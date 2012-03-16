/**
 * 
 */
package jp.co.shantery.spring.web.support.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import jp.co.shantery.spring.web.support.csv.bean.MyBean;
import jp.co.shantery.spring.web.support.csv.reader.CsvFileReader;
import jp.co.shantery.spring.web.support.csv.reader.impl.DefaultCsvFileReaderImpl;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link CsvFileReader}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class CsvFileReaderTest {

	/** テスト用CSVファイル名です。 */
	private static final String FILE_NAME = "csv_file_parser_test.csv";

	private String filePath;

	@Before
	public void setUp() throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		filePath = loader.getResource(FILE_NAME).getPath();
	}

	@Test
	public void readAllLines() throws Exception {
		CsvFileReader reader = new DefaultCsvFileReaderImpl();
		reader.setFile(new File(filePath));
		List<MyBean> list = reader.readAllLines(MyBean.class);

		assertNotNull(list);

		for (MyBean bean : list) {
			assertEquals("テストタロウ", bean.getName());
			assertEquals(Integer.valueOf(30), bean.getAge());
			assertEquals("システム部", bean.getDeptName());
			assertEquals(Double.valueOf("200000"), bean.getSalary());
		}
	}

	@Test
	public void iterator() throws Exception {
		CsvFileReader parser = new DefaultCsvFileReaderImpl();
		parser.setFile(new File(filePath));
		Iterator<MyBean> itr = parser.iterator(MyBean.class);

		int count = 0;
		while (itr.hasNext()) {
			MyBean bean = itr.next();
			assertEquals("テストタロウ", bean.getName());
			assertEquals(Integer.valueOf(30), bean.getAge());
			assertEquals("システム部", bean.getDeptName());
			assertEquals(Double.valueOf("200000"), bean.getSalary());
			count++;
		}
		if (count <= 0) {
			fail("一度もイテレータが呼び出されてない");
		}

	}

}

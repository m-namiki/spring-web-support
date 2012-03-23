/**
 * 
 */
package jp.co.shantery.spring.web.support.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.co.shantery.spring.web.support.csv.bean.MyBean;
import jp.co.shantery.spring.web.support.csv.writer.CsvFileWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * {@link CsvFileWriter}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class CsvFileWriterTest {

	/** テスト用CSVファイル名です。 */
	private static final String FILE_NAME = "csv_file_writer_test.csv";

	/** テスト対象クラスです。 */
	@Autowired
	private CsvFileWriter csvFileWriter;

	public void setUp() {

	}

	@Test
	public void writeAll() throws Exception {

		File file = new File(System.getProperty("java.io.tmpdir"), FILE_NAME);

		csvFileWriter.setFile(file);

		MyBean bean = new MyBean();
		bean.setName("テストタロウ");
		bean.setAge(Integer.valueOf(30));
		bean.setEmployeeNo(Integer.valueOf(100));
		bean.setDeptName("総務部");
		bean.setSalary(Double.valueOf("150000"));

		List<MyBean> list = new ArrayList<>();
		list.add(bean);

		csvFileWriter.writeAll(list);
	}

}

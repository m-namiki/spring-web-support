/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.factory;

import jp.co.shantery.spring.web.support.csv.writer.CsvFileWriter;
import jp.co.shantery.spring.web.support.csv.writer.impl.DefaultCsvFileWriterImpl;

import org.springframework.beans.factory.FactoryBean;

/**
 * CSVファイルライターを作成するファクトリークラスです。
 * 
 * @author m-namiki
 * 
 */
public class CsvFileWriterFactory implements FactoryBean<CsvFileWriter> {

	@Override
	public CsvFileWriter getObject() throws Exception {
		return new DefaultCsvFileWriterImpl();
	}

	@Override
	public Class<?> getObjectType() {
		return CsvFileWriter.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}

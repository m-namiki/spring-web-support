/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.factory;

import jp.co.shantery.spring.web.support.csv.reader.CsvFileReader;
import jp.co.shantery.spring.web.support.csv.reader.impl.DefaultCsvFileReaderImpl;

import org.springframework.beans.factory.FactoryBean;

/**
 * CSVファイルリーダーを作成するファクトリークラスです。
 * 
 * @author m-namiki
 * 
 */
public class CsvFileReaderFactory implements FactoryBean<CsvFileReader> {

	@Override
	public CsvFileReader getObject() throws Exception {
		return new DefaultCsvFileReaderImpl();
	}

	@Override
	public Class<? extends CsvFileReader> getObjectType() {
		return CsvFileReader.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}

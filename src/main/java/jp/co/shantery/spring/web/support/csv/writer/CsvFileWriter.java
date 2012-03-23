/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.writer;

import java.io.File;
import java.util.Collection;

/**
 * CSVファイルを書き出すためのインターフェースです。
 * 
 * @author m-namiki
 * 
 */
public interface CsvFileWriter {

	/**
	 * 書き出すファイルを指定します。
	 * 
	 * @param file
	 *            ファイル
	 */
	void setFile(File file);

	/**
	 * 指定されたオブジェクトを書き出します。
	 * 
	 * @param obj
	 *            オブジェクト
	 */
	void write(Object obj);

	/**
	 * 書き出しをクローズします。<br>
	 * {@link CsvFileWriter#write(Object)}を利用した場合、必ずこのメソッドを呼び出してください。
	 */
	void close();

	/**
	 * 指定されたオブジェクトのリストを書き出します。
	 * 
	 * @param collection
	 *            コレクション
	 */
	void writeAll(Collection<?> collection);

}

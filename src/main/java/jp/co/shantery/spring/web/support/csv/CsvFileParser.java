/**
 * 
 */
package jp.co.shantery.spring.web.support.csv;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * CSVファイルを読み込むためのインターフェースです。
 * 
 * @author m-namiki
 * 
 */
public interface CsvFileParser {

	/**
	 * 読み込むファイルを指定します。
	 * 
	 * @param file
	 *            対象ファイル
	 */
	void setFile(File file);

	/**
	 * イテレータを返却します。
	 * 
	 * @param clazz
	 *            型
	 * @return イテレータ
	 */
	<T> Iterator<T> iterator(Class<T> clazz);

	/**
	 * ファイルを全行読み込んで型パラメータで指定されたクラスのリストを返却します。
	 * 
	 * @param clazz
	 *            型
	 * @return リスト
	 */
	<T> List<T> readAllLines(Class<T> clazz);
}

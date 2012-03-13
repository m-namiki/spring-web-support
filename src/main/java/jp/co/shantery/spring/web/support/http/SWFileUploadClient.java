/**
 * 
 */
package jp.co.shantery.spring.web.support.http;

import java.io.File;

/**
 * HTTP通信でファイルアップロードを行うインターフェースです。
 * 
 * @author pcuser
 * 
 */
public interface SWFileUploadClient {

	/**
	 * 指定したURLにファイルをアップロードします。
	 * 
	 * @param returnType
	 *            戻り値の型
	 * @param targetUrl
	 *            ポストするURL
	 * @param paramName
	 *            アップロードファイルのパラメータ名
	 * @param uploadFile
	 *            アップロードファイル
	 * @return 戻り値の型で指定されたクラスのオブジェクト
	 */
	<T> T post(Class<T> returnType, String targetUrl, String paramName,
			File uploadFile);

}

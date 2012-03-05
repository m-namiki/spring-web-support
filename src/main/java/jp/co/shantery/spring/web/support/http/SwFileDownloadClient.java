/**
 * 
 */
package jp.co.shantery.spring.web.support.http;

/**
 * HTTP通信でファイルのダウンロードを行うインターフェースです。
 * 
 * @author m-namiki
 * 
 */
public interface SwFileDownloadClient {

	/**
	 * 指定したURLからファイルをダウンロードします。
	 * 
	 * @param returnType
	 *            戻り値の型
	 * @param targetUrl
	 *            ポストするURL
	 * @param src
	 *            JSONに変換するオブジェクト
	 * @param distPath
	 *            ダウンロードファイルの出力先パス
	 * @return ファイルまたは戻り値の型で指定されたクラスのオブジェクト
	 */
	<T> T post(Class<T> returnType, String targetUrl, Object src,
			String distPath);

}

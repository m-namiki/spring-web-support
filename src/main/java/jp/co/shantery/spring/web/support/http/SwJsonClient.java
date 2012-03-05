/**
 * 
 */
package jp.co.shantery.spring.web.support.http;

/**
 * HTTP通信でJSONの送受信を行うインターフェースです。
 * 
 * @author m-namiki
 * 
 */
public interface SwJsonClient {

	/**
	 * 指定されたURLにJSONをポストします。
	 * 
	 * @param returnType
	 *            戻り値の型
	 * @param targetUrl
	 *            ポストするURL
	 * @param src
	 *            JSONに変換するオブジェクト
	 * @return 戻り値の型で指定されたクラスのオブジェクト
	 */
	<T> T post(Class<T> returnType, String targetUrl, Object src);

}

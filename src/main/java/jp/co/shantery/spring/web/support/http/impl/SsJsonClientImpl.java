/**
 * 
 */
package jp.co.shantery.spring.web.support.http.impl;

import jp.co.shantery.spring.web.support.http.SsJsonClient;

import org.apache.http.client.methods.HttpPost;

/**
 * {@link SsJsonClient}の実装クラスです。
 * 
 * @author m-namiki
 * 
 */
public class SsJsonClientImpl extends BaseHttpClient implements SsJsonClient {

	/**
	 * 指定されたURLにJSONをポストします。
	 * 
	 * @param <T>
	 *            型
	 * @param returnType
	 *            戻り値の型
	 * @param targetUrl
	 *            ポストするURL
	 * @param src
	 *            JSONに変換するオブジェクト
	 * @return 戻り値の型で指定されたクラスのオブジェクト
	 */
	public <T> T post(Class<T> returnType, String targetUrl, Object src) {
		HttpPost postRequest = new HttpPost(targetUrl);
		return execute(returnType, setHttpPostHeader(postRequest),
				createEntity(src));
	}

}

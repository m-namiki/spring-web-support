/**
 * 
 */
package jp.co.shantery.spring.web.support.http.impl;

import jp.co.shantery.spring.web.support.http.SwJsonClient;

import org.apache.http.client.methods.HttpPost;

/**
 * {@link SwJsonClient}の実装クラスです。
 * 
 * @author m-namiki
 * 
 */
public class SwJsonClientImpl extends AbstractHttpClient implements SwJsonClient {

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

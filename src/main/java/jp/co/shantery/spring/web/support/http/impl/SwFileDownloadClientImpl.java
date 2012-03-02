/**
 * 
 */
package jp.co.shantery.spring.web.support.http.impl;

import jp.co.shantery.spring.web.support.http.SsFileDownloadClient;

import org.apache.http.client.methods.HttpPost;

/**
 * HTTP通信でファイルのダウンロードを行うクラスです。
 * 
 * @author m-namiki
 * 
 */
public class SwFileDownloadClientImpl extends BaseHttpClient implements
		SsFileDownloadClient {

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
	@SuppressWarnings("unchecked")
	public <T> T post(Class<T> returnType, String targetUrl, Object src,
			String distPath) {
		HttpPost postRequest = new HttpPost(targetUrl);
		return (T) executeDownloadOrJson(returnType,
				setHttpPostHeader(postRequest), createEntity(src), distPath);
	}

}

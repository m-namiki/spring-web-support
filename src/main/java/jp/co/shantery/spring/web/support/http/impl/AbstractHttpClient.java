/**
 * 
 */
package jp.co.shantery.spring.web.support.http.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import jp.co.shantery.spring.web.support.exception.HttpClientRuntimeException;
import jp.co.shantery.spring.web.support.http.NoneSSLVerifierHttpClient;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * HTTP通信を行う抽象クラスです。
 * 
 * @author m-namiki
 * 
 */
public abstract class AbstractHttpClient {

	private static Log logger = LogFactory.getLog(AbstractHttpClient.class);

	private static final Integer TIME_OUT_SECCOND = 20;

	/** ACCEPTのヘッダ定義です。 */
	static final String ACCEPT = "ACCEPT";

	/** ACCEPTに設定する値です。 */
	static final String ACCEPT_VALUE = "application/json";

	/** CONTENT_TYPEに設定する値です。 */
	static final String CONTENT_TYPE_VALUE = "application/json; charset=UTF-8";

	/**
	 * 通信処理を行います。
	 * 
	 * @param <T>
	 *            型
	 * @param returnType
	 *            返却値の型
	 * @param postRequest
	 *            ポストリクエスト
	 * @param entity
	 *            エンティティ
	 * @return 型で指定されたオブジェクト
	 */
	protected static <T> T execute(Class<T> returnType, HttpPost postRequest,
			HttpEntity entity) {

		HttpParams params = new BasicHttpParams();
		// タイムアウト時間の指定
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT_SECCOND);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT_SECCOND);

		HttpClient httpclient = new NoneSSLVerifierHttpClient(params);
		postRequest.setEntity(entity);
		postRequest.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);

		HttpResponse httpResponse = null;
		try {
			httpResponse = (HttpResponse) httpclient.execute(postRequest);
		} catch (Exception e) {
			logger.warn("Failed to send a request.", e);
			throw new HttpClientRuntimeException(e);
		} finally {
			postRequest.abort();
		}
		if (HttpStatus.SC_OK != httpResponse.getStatusLine().getStatusCode()) {
			logger.warn("Status code is incorrect. : "
					+ httpResponse.getStatusLine().getStatusCode());
			throw new HttpClientRuntimeException(httpResponse.getStatusLine()
					.getStatusCode());
		}
		try {
			HttpEntity responseEntity = httpResponse.getEntity();
			if (null != responseEntity) {
				return new ObjectMapper().readValue(
						responseEntity.getContent(), returnType);
			}
		} catch (Exception e) {
			logger.warn("Failed to receive a response.", e);
			throw new HttpClientRuntimeException(e);
		}

		return null;
	}

	/**
	 * ファイルダウンロードを伴うHTTP通信を行います。
	 * 
	 * @param returnType
	 *            返却値の型
	 * @param postRequest
	 *            ポストリクエスト
	 * @param entity
	 *            エンティティ
	 * @param distPath
	 *            ダウンロードファイルのパス
	 * @return 正しくダウンロードが行えた場合は{@link File}、ダウンロードが失敗した場合は返却値の型
	 */

	protected static Object executeDownloadOrJson(Class<?> returnType,
			HttpPost postRequest, HttpEntity entity, String distPath) {

		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT_SECCOND);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT_SECCOND);

		HttpClient httpclient = new NoneSSLVerifierHttpClient(params);
		postRequest.setEntity(entity);
		postRequest.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);

		HttpResponse httpResponse = null;
		OutputStream out = null;
		try {
			httpResponse = httpclient.execute(postRequest);

			if (HttpStatus.SC_OK != httpResponse.getStatusLine()
					.getStatusCode()) {
				logger.warn("Status code is incorrect. : "
						+ httpResponse.getStatusLine().getStatusCode());
				throw new HttpClientRuntimeException(httpResponse
						.getStatusLine().getStatusCode());
			}

			HttpEntity responseEntity = httpResponse.getEntity();
			if (null != responseEntity) {
				Header contentType = responseEntity.getContentType();
				if (contentType.getValue().contains(HTTP.OCTET_STREAM_TYPE)) {
					// ファイルの場合
					Header[] header = httpResponse
							.getHeaders("Content-disposition");
					HeaderElement[] elements = header[0].getElements();
					NameValuePair pair = elements[0]
							.getParameterByName("filename");
					File file = new File(distPath, pair.getValue());
					out = new FileOutputStream(file);
					responseEntity.writeTo(out);
					out.flush();
					return file;

				} else if (contentType.getValue().contains("application/json")) {
					// jsonの場合
					return new ObjectMapper().readValue(
							responseEntity.getContent(), returnType);
				}
			}
		} catch (HttpClientRuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.warn("Failed to send a request or receive a response.", e);
			throw new HttpClientRuntimeException(e);
		} finally {
			if (null != postRequest) {
				postRequest.abort();
			}
			IOUtils.closeQuietly(out);
		}
		return null;
	}

	/**
	 * 指定されたオブジェクトをJSONに変換します。
	 * 
	 * @param src
	 *            変換するオブジェクト
	 * @return HTTPエンティティ
	 */
	protected HttpEntity createEntity(Object src) {
		StringEntity se = null;
		try {
			se = new StringEntity(new ObjectMapper().writeValueAsString(src));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return se;
	}

	/**
	 * JSONをポストする場合のHTTPヘッダを指定します。
	 * 
	 * @param postRequest
	 *            リクエスト
	 * @return リクエスト
	 */
	protected HttpPost setHttpPostHeader(HttpPost postRequest) {
		postRequest.setHeader(ACCEPT, ACCEPT_VALUE);
		postRequest.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_VALUE);
		return postRequest;
	}
}

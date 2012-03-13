/**
 * 
 */
package jp.co.shantery.spring.web.support.http.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Date;

import jp.co.shantery.spring.web.support.exception.UnsupportedEncodingRuntimeException;
import jp.co.shantery.spring.web.support.util.SWClassUtils;
import jp.co.shantery.spring.web.support.util.SWDateUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

/**
 * HTTP通信でファイルのアップロードを行うクラスです。
 * 
 * @author m-namiki
 * 
 */
public class SWFileUploadClientImpl extends AbstractHttpClient {

	/**
	 * 指定したURLにファイルをアップロードします。
	 * 
	 * @param <T>
	 *            型
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
	public static <T> T post(Class<T> returnType, String targetUrl,
			String paramName, File uploadFile) {
		return execute(returnType, new HttpPost(targetUrl),
				createEntity(paramName, uploadFile));
	}

	/**
	 * 指定したURLにファイルをアップロードします。<br>
	 * その際にリクエストパラメータも指定します。
	 * 
	 * @param <T>
	 *            型
	 * @param returnType
	 *            戻り値の型
	 * @param targetUrl
	 *            ポストするURL
	 * @param paramName
	 *            アップロードファイルのパラメータ名
	 * @param uploadFile
	 *            アップロードファイル
	 * @param param
	 *            リクエストパラメータに指定するオブジェクト
	 * @return 戻り値の型で指定されたクラスのオブジェクト
	 */
	public static <T> T postWithParam(Class<T> returnType, String targetUrl,
			String paramName, File uploadFile, Object param) {
		return execute(returnType, new HttpPost(targetUrl),
				createEntityWithParam(paramName, uploadFile, param));
	}

	/**
	 * アップロードファイルをHTTPエンティティに設定します。
	 * 
	 * @param paramName
	 *            パラメータ名
	 * @param uploadFile
	 *            アップロードファイル
	 * @return HTTPエンティティ
	 */
	private static HttpEntity createEntity(String paramName, File uploadFile) {
		MultipartEntity entity = new MultipartEntity();
		entity.addPart(paramName, new FileBody(uploadFile));
		return entity;
	}

	/**
	 * アップロードファイルとリクエストパラメータをHTTPエンティティに設定します。
	 * 
	 * @param paramName
	 *            パラメータ名
	 * @param uploadFile
	 *            アップロードファイル
	 * @param param
	 *            リクエストパラメータ
	 * @return HTTPエンティティ
	 */
	private static HttpEntity createEntityWithParam(String paramName,
			File uploadFile, Object param) {
		MultipartEntity entity = (MultipartEntity) createEntity(paramName,
				uploadFile);
		try {
			Class<?> clazz = param.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				ContentBody body = null;
				Object obj = SWClassUtils.get(field, param);
				if (obj instanceof String) {
					body = new StringBody((String) obj);
				} else if (obj instanceof Number) {
					body = new StringBody(String.valueOf(obj));
				} else if (obj instanceof Date) {
					body = new StringBody(
							(String) SWDateUtils.formatDateToString((Date) obj,
									"yyyy/MM/dd HH:mm:ss"));
				}
				entity.addPart(field.getName(), body);
			}
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedEncodingRuntimeException(e);
		}
		return entity;
	}
}

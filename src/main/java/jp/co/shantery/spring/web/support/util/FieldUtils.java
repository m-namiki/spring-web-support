/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import java.lang.reflect.Field;

import jp.co.shantery.spring.web.support.exception.IllegalAccessRuntimeException;

/**
 * {@link Field}を操作するユーティリティクラスです。
 * 
 * @author m-namiki
 * 
 */
public class FieldUtils {

	/**
	 * プライベートなコンストラクタです。<br>
	 * 外部からのインスタンス生成は行えません。
	 */
	private FieldUtils() {
	}

	/**
	 * 指定されたオブジェクトのフィールドから値を取得します。
	 * 
	 * @param field
	 *            フィールド
	 * @param obj
	 *            オブジェクト
	 * @return フィールドの値
	 */
	public static Object get(Field field, Object obj) {
		try {
			return field.get(obj);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		}
	}
}

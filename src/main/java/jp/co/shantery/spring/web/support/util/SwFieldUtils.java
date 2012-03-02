/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import java.lang.reflect.Field;

import jp.co.shantery.spring.web.support.exception.IllegalAccessRuntimeException;
import jp.co.shantery.spring.web.support.exception.NoSuchFieldRuntimeException;

/**
 * {@link Field}を操作するユーティリティクラスです。
 * 
 * @author m-namiki
 * 
 */
public class SwFieldUtils {

	/**
	 * プライベートなコンストラクタです。<br>
	 * 外部からのインスタンス生成は行えません。
	 */
	private SwFieldUtils() {
	}

	/**
	 * 指定されたクラスから指定された名称のフィールドを取得して返却します。
	 * 
	 * @param clazz
	 *            クラス
	 * @param name
	 *            フィールド名
	 * @return フィールド
	 */
	public static Field getField(Class<?> clazz, String name) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(name);
		} catch (SecurityException e) {
			throw e;
		} catch (NoSuchFieldException e) {
			throw new NoSuchFieldRuntimeException(e.getMessage(), e);
		}
		return field;
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
			field.setAccessible(true);
			return field.get(obj);
		} catch (IllegalArgumentException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e.getMessage(), e);
		}
	}
}

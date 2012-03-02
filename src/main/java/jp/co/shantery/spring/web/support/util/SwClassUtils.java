/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import java.lang.reflect.Field;

import jp.co.shantery.spring.web.support.exception.IllegalAccessRuntimeException;
import jp.co.shantery.spring.web.support.exception.NoSuchFieldRuntimeException;

/**
 * クラスに関する操作を行うユーティリティクラスです。
 * 
 * @author m-namiki
 * 
 */
public class SwClassUtils {

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
			throw new NoSuchFieldRuntimeException(e);
		}
		return field;
	}

	/**
	 * 指定されたオブジェクトからフィールドの値を取得して返却します。
	 * 
	 * @param field
	 *            フィールド
	 * @param obj
	 *            オブジェクト
	 * @return フィールドの値
	 */
	public static Object get(Field field, Object obj) {
		Object o = null;
		try {
			field.setAccessible(true);
			o = field.get(obj);

		} catch (IllegalArgumentException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e);
		}
		return o;
	}
}

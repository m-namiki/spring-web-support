/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import java.lang.reflect.Type;

import jp.co.shantery.spring.web.support.exception.IllegalAccessRuntimeException;

/**
 * クラスに関する操作を行うユーティリティクラスです。
 * 
 * @author m-namiki
 * 
 */
public class SWClassUtils {

	/**
	 * 指定されたクラスのインスタンスを返却します。
	 * 
	 * @param clazz
	 *            クラス
	 * @return インスタンス
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessRuntimeException(e.getMessage(), e);
		}
	}

	public static Type getParameterType(Object obj) {
		return obj.getClass().getGenericSuperclass();
	}

}

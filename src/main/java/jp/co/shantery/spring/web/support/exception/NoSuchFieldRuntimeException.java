/**
 * 
 */
package jp.co.shantery.spring.web.support.exception;

/**
 * {@link java.lang.NoSuchFieldException}のラッパー例外クラスです。
 * 
 * @author m-namiki
 * 
 */
public class NoSuchFieldRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -374832257343710536L;
	
	public NoSuchFieldRuntimeException(NoSuchFieldException e) {
		super(e);
	}
	public NoSuchFieldRuntimeException(String message, NoSuchFieldException e) {
		super(message, e);
	}
}

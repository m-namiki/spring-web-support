/**
 * 
 */
package jp.co.shantery.spring.web.support.exception;

/**
 * {@link IllegalAccessException}をラップする例外クラスです。
 * 
 * @author m-namiki
 * 
 */
public class IllegalAccessRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4648044506374342086L;

	public IllegalAccessRuntimeException(IllegalAccessException cause) {
		super(cause);
	}

	public IllegalAccessRuntimeException(String message,
			IllegalAccessException cause) {
		super(message, cause);
	}

}

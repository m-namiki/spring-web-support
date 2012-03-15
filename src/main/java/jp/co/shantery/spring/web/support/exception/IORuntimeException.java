/**
 * 
 */
package jp.co.shantery.spring.web.support.exception;

import java.io.IOException;

/**
 * {@link IOException}をラップする例外クラスです。
 * 
 * @author m-namiki
 * 
 */
public class IORuntimeException extends RuntimeException {

	private static final long serialVersionUID = -6949564585205842331L;

	public IORuntimeException(IOException cause) {
		super(cause);
	}

	public IORuntimeException(String message, IOException cause) {
		super(message, cause);
	}
}

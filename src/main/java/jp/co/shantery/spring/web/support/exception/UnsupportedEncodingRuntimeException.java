/**
 * 
 */
package jp.co.shantery.spring.web.support.exception;

import java.io.UnsupportedEncodingException;

/**
 * {@link UnsupportedEncodingException}をラップする実行時例外です。
 * 
 * @author m-namiki
 * 
 */
public class UnsupportedEncodingRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -7584703599554377702L;

	public UnsupportedEncodingRuntimeException(
			UnsupportedEncodingException cause) {
		super(cause);
	}

}

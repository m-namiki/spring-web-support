/**
 * 
 */
package jp.co.shantery.spring.web.support.exception;

/**
 * Http通信を行った場合の例外クラスです。
 * 
 * @author m-namiki
 * 
 */
public class HttpClientRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer statusCode;

	public HttpClientRuntimeException(Throwable cause) {
		super(cause);
	}

	public HttpClientRuntimeException(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}
}

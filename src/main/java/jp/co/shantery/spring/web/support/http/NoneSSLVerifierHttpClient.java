/**
 * 
 */
package jp.co.shantery.spring.web.support.http;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;

/**
 * サーバ証明書の存在を無視してSSL通信を行うための{@link HttpClient}です。
 * 
 * @author m-namiki
 * 
 */
public class NoneSSLVerifierHttpClient extends DefaultHttpClient {

	public NoneSSLVerifierHttpClient(final HttpParams params) {
		super(null, params);
	}

	@Override
	protected ClientConnectionManager createClientConnectionManager() {
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", 80, PlainSocketFactory
				.getSocketFactory()));
		SSLSocketFactory sslSocketFactory = null;
		try {
			sslSocketFactory = NoneSSLVerifierSSLSocketFactory.create();
		} catch (KeyManagementException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		registry.register(new Scheme("https", 443, sslSocketFactory));

		ClientConnectionManager connManager = null;
		HttpParams params = getParams();

		ClientConnectionManagerFactory factory = null;

		String className = (String) params
				.getParameter(ClientPNames.CONNECTION_MANAGER_FACTORY_CLASS_NAME);
		if (className != null) {
			try {
				Class<?> clazz = Class.forName(className);
				factory = (ClientConnectionManagerFactory) clazz.newInstance();
			} catch (ClassNotFoundException ex) {
				throw new IllegalStateException("Invalid class name: "
						+ className);
			} catch (IllegalAccessException ex) {
				throw new IllegalAccessError(ex.getMessage());
			} catch (InstantiationException ex) {
				throw new InstantiationError(ex.getMessage());
			}
		}
		if (factory != null) {
			connManager = factory.newInstance(params, registry);
		} else {
			connManager = new SingleClientConnManager(registry);
		}
		return connManager;
	}

}

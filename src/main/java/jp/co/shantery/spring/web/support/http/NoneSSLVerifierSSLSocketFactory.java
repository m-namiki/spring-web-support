/**
 * 
 */
package jp.co.shantery.spring.web.support.http;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/**
 * サーバ証明書の存在を無視してSSL通信を行うための{@link SSLSocketFactory}です。
 * 
 * @author m-namiki
 * 
 */
public class NoneSSLVerifierSSLSocketFactory extends SSLSocketFactory {

	private final SSLContext sslcontext;

	public NoneSSLVerifierSSLSocketFactory(final SSLContext sslContext,
			final X509HostnameVerifier hostnameVerifier) {
		super(sslContext, hostnameVerifier);
		sslcontext = sslContext;
	}

	public static NoneSSLVerifierSSLSocketFactory create()
			throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		} }, null);
		return new NoneSSLVerifierSSLSocketFactory(sslContext,
				ALLOW_ALL_HOSTNAME_VERIFIER);
	}

	@Override
	public Socket createSocket() throws IOException {
		return sslcontext.getSocketFactory().createSocket();
	}
}

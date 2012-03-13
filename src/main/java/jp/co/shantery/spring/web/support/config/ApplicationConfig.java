/**
 * 
 */
package jp.co.shantery.spring.web.support.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * アプリケーションの設定情報を保持するクラスです。
 * 
 * @author m-namiki
 * 
 */
public class ApplicationConfig {

	private final Log logger = LogFactory.getLog(getClass());

	/** デフォルトのコンテキストファイル名です。 */
	protected static final String DEFAULT_CONTEXT_FILE = "applicationContext.xml";

	/** アプリケーションコンテキストです。 */
	protected ApplicationContext context;

	/**
	 * コンストラクタです。
	 */
	public ApplicationConfig() {
		this(DEFAULT_CONTEXT_FILE);
	}

	/**
	 * コンストラクタです。
	 * 
	 * @param contextName
	 *            コンテキストファイル名
	 */
	public ApplicationConfig(String contextName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Context File : " + contextName);
		}
		context = new ClassPathXmlApplicationContext(contextName);
	}

	/**
	 * 指定されたサービスを作成します。
	 * 
	 * @param <T>
	 *            型
	 * @param type
	 *            型
	 * @return サービスのインスタンス
	 */
	public <T> T createService(Class<T> type) {
		return context.getBean(type);
	}
}

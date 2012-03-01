/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jp.co.shantery.spring.web.support.annotation.HiddenParam;
import jp.co.shantery.spring.web.support.command.HiddenEnbededCommand;

/**
 * 画面にHiddenタグでパラメータを埋め込むためのユーティリティクラスです。
 * 
 * @author m-namiki
 * 
 */
public class HiddenTagUtils {

	/** Hidden用パラメータをリクエストに保存する場合のキー名です。 */
	public static final String HIDDEN_PARAM_KEY = "jp.co.shantery.spring.web.support.HiddenParams";

	/**
	 * プライベートなコンストラクタです。<br>
	 * 外部からのインスタンス生成は行えません。
	 */
	private HiddenTagUtils() {
	}

	/**
	 * リクエストにHidden用パラメータを保存します。<br>
	 * 保存する対象は{@link HiddenParam}が付与されているパラメータのみです。<br>
	 * コマンドが<code>null</code>の場合、リクエストからHidden用パラメータを削除します。
	 * 
	 * @param request
	 *            リクエスト
	 * @param command
	 *            コマンド
	 */
	public static void saveHiddenParams(HttpServletRequest request,
			HiddenEnbededCommand command) {

		if (null == command) {
			request.removeAttribute(HIDDEN_PARAM_KEY);
			return;
		}

		Map<String, String> map = new HashMap<>();
		Field[] fields = command.getClass().getFields();
		for (Field field : fields) {
			HiddenParam param = field.getAnnotation(HiddenParam.class);
			if (null != param) {
				Object fieldValue = FieldUtils.get(field, command);
				String value = null;
				if (fieldValue instanceof String) {
					value = (String) fieldValue;
				} else if (fieldValue instanceof Boolean) {
					value = ((Boolean) fieldValue).toString();
				} else if (fieldValue instanceof Number) {
					value = String.valueOf((Number) fieldValue);
				}
				if (null == value) {
					value = "";
				}
				map.put(field.getName(), value);
			}
		}
		request.setAttribute(HIDDEN_PARAM_KEY, map);
	}
}

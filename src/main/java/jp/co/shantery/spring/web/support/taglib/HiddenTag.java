/**
 * 
 */
package jp.co.shantery.spring.web.support.taglib;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jp.co.shantery.spring.web.support.util.HiddenTagUtils;

/**
 * リクエストに設定されているパラメータをhiddenタグとして画面に埋め込むタグライブラリです。
 * 
 * @author m-namiki
 * 
 */
public class HiddenTag extends TagSupport {

	private static final long serialVersionUID = 4100612169032777549L;

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		Map<String, String> hiddenParams = null;

		try {
			Object obj = pageContext
					.findAttribute(HiddenTagUtils.HIDDEN_PARAM_KEY);
			if (null != obj) {
				hiddenParams = (Map<String, String>) obj;
				Iterator<String> itr = hiddenParams.keySet().iterator();

				while (itr.hasNext()) {
					String key = itr.next();
					StringBuilder inputTag = new StringBuilder(
							"<input type=\"hidden\" name=");
					inputTag.append("\"" + key + "\"");
					inputTag.append(" value=\"" + hiddenParams.get(key)
							+ "\"/>\n");
					pageContext.getOut().write(inputTag.toString());
				}
			}
		} catch (Exception e) {
			throw new JspException(e.getMessage(), e);
		}
		return EVAL_BODY_INCLUDE;
	}

}

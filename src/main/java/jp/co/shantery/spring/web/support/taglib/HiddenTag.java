/**
 * 
 */
package jp.co.shantery.spring.web.support.taglib;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import jp.co.shantery.spring.web.support.util.HiddenTagUtils;

/**
 * リクエストに保存されているhiddenタグ用パラメータをHTMLに埋め込むタグライブラリです。
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

		Object obj = pageContext.findAttribute(HiddenTagUtils.HIDDEN_PARAMS_KEY);
		if (null != obj) {
			hiddenParams = (Map<String, String>) obj;
			Iterator<Entry<String, String>> itr = hiddenParams.entrySet()
					.iterator();

			while (itr.hasNext()) {
				Entry<String, String> entry = itr.next();
				StringBuilder text = new StringBuilder();
				text.append("<input id=\"");
				text.append(entry.getKey());
				text.append("\"");
				text.append(" name=\"");
				text.append(entry.getKey());
				text.append("\"");
				text.append(" type=\"hidden\"");
				text.append(" value=\"");
				text.append(entry.getValue());
				text.append("\"/>\n");

				try {
					pageContext.getOut().write(text.toString());
				} catch (IOException e) {
					throw new JspException(e);
				}
			}
		}
		return EVAL_BODY_INCLUDE;
	}

}

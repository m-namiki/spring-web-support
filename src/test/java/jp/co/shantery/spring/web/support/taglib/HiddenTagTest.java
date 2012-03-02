/**
 * 
 */
package jp.co.shantery.spring.web.support.taglib;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import jp.co.shantery.spring.web.support.util.HiddenTagUtils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;

/**
 * {@link HiddenTag}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class HiddenTagTest {

	private PageContext pageContext;
	private HiddenTag hiddenTag;

	@Before
	public void setUp() {
		pageContext = new MockPageContext();
		hiddenTag = new HiddenTag();
		hiddenTag.setPageContext(pageContext);
	}

	@Test
	public void doStartTag() throws Exception {
		Map<String, String> hiddenParams = new HashMap<String, String>();
		hiddenParams.put("key", "value");
		pageContext
				.setAttribute(HiddenTagUtils.HIDDEN_PARAMS_KEY, hiddenParams);

		int result = hiddenTag.doStartTag();
		assertEquals(TagSupport.EVAL_BODY_INCLUDE, result);

		String output = ((MockHttpServletResponse) pageContext.getResponse())
				.getContentAsString();
		assertEquals(
				"<input id=\"key\" name=\"key\" type=\"hidden\" value=\"value\"/>\n",
				output);
	}
}

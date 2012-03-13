/**
 * 
 */
package jp.co.shantery.spring.web.support.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import jp.co.shantery.spring.web.support.exception.NoSuchFieldRuntimeException;
import jp.co.shantery.spring.web.support.util.bean.MyBean;

import org.junit.Test;

/**
 * {@link SWFieldUtils}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class SwFieldUtilsTest {

	@Test(expected = NoSuchFieldRuntimeException.class)
	public void getField() {
		Field nameField = SWFieldUtils.getField(MyBean.class, "name");
		assertNotNull(nameField);
		assertEquals("name", nameField.getName());
		assertEquals(String.class, nameField.getType());

		Field ageField = SWFieldUtils.getField(MyBean.class, "age");
		assertNotNull(ageField);
		assertEquals("age", ageField.getName());
		assertEquals(Integer.class, ageField.getType());

		SWFieldUtils.getField(MyBean.class, "address");
	}

	@Test
	public void get() {
		MyBean bean = new MyBean();
		bean.setName("SpringWebSupport");
		bean.setAge(10);

		Field nameField = SWFieldUtils.getField(MyBean.class, "name");
		assertEquals("SpringWebSupport", SWFieldUtils.get(nameField, bean));

		Field ageField = SWFieldUtils.getField(MyBean.class, "age");
		assertEquals(Integer.valueOf(10), SWFieldUtils.get(ageField, bean));
	}

}

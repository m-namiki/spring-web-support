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
 * {@link SwFieldUtils}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class SwFieldUtilsTest {

	@Test(expected = NoSuchFieldRuntimeException.class)
	public void getField() {
		Field nameField = SwFieldUtils.getField(MyBean.class, "name");
		assertNotNull(nameField);
		assertEquals("name", nameField.getName());
		assertEquals(String.class, nameField.getType());

		Field ageField = SwFieldUtils.getField(MyBean.class, "age");
		assertNotNull(ageField);
		assertEquals("age", ageField.getName());
		assertEquals(Integer.class, ageField.getType());

		SwFieldUtils.getField(MyBean.class, "address");
	}

	@Test
	public void get() {
		MyBean bean = new MyBean();
		bean.setName("SpringWebSupport");
		bean.setAge(10);

		Field nameField = SwFieldUtils.getField(MyBean.class, "name");
		assertEquals("SpringWebSupport", SwFieldUtils.get(nameField, bean));

		Field ageField = SwFieldUtils.getField(MyBean.class, "age");
		assertEquals(Integer.valueOf(10), SwFieldUtils.get(ageField, bean));
	}

}

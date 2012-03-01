/**
 * 
 */
package jp.co.shantery.spring.web.support.bean;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link BeanConverter}のテストケースです。
 * 
 * @author m-namiki
 * 
 */
public class BeanConverterTest {

	private MyCommand command;

	@Before
	public void setUp() {

		command = new MyCommand();
		command.setAccessCode("30000000000000000001");
		command.setOperationCode("1");
		command.setOperationDateFrom("2011/11/01 12:00:00");
		command.setOperationDateTo("");
		command.setRevokeReason("9");
	}

	@Test
	public void convert() {
		MyCondition condition = BeanConverter.convert(command,
				MyCondition.class);
		assertNotNull(condition);
		assertEquals("30000000000000000001", condition.getAccessCode());
		assertEquals(Integer.valueOf(1), condition.getOperationCode());
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 10, 1, 12, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		assertEquals(cal.getTime(), condition.getOperationDateFrom());
		assertNull(condition.getOperationDateTo());
		assertEquals(Byte.valueOf("9"), condition.getRevokeReason());
	}

}

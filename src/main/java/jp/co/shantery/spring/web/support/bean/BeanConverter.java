/**
 * 
 */
package jp.co.shantery.spring.web.support.bean;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * JavaBeanのコピー時に自動で型変換を行うコンバータクラスです。<br>
 * {@link BeanUtils#copyProperties(Object, Object)}
 * ではプロパティ名、型が同じでなければコピーできませんが、当クラスではプロパティ名が同一であれば型は異なってもコピーできます。
 * 
 * @author m-namiki
 * 
 */
public class BeanConverter {

	/** 型変換サービスです。 */
	private static ConversionService conversionService;

	static {
		FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
		factoryBean.afterPropertiesSet();
		conversionService = factoryBean.getObject();
	}

	/**
	 * 指定されたオブジェクトをコピーします。
	 * 
	 * @param source
	 *            オブジェクト
	 * @param clazz
	 *            コピー先の型
	 * @return コピーしたオブジェクト
	 */
	public static <T> T convert(Object source, Class<T> clazz) {

		T target = BeanUtils.instantiate(clazz);

		BeanWrapper sourceWrapper = PropertyAccessorFactory
				.forBeanPropertyAccess(source);
		PropertyDescriptor[] sourceDescs = sourceWrapper
				.getPropertyDescriptors();

		BeanWrapper targetWrapper = PropertyAccessorFactory
				.forBeanPropertyAccess(target);

		String propertyName = null;
		for (PropertyDescriptor sourceDesc : sourceDescs) {
			propertyName = sourceDesc.getName();

			TypeDescriptor typeDesc = targetWrapper
					.getPropertyTypeDescriptor(propertyName);
			if (null == typeDesc) {
				continue;
			}
			if (!targetWrapper.isWritableProperty(propertyName)) {
				continue;
			}

			Object value = sourceWrapper.getPropertyValue(propertyName);
			if (null == value || "".equals(value)) {
				targetWrapper.setPropertyValue(propertyName, null);
			} else {
				targetWrapper.setPropertyValue(propertyName,
						conversionService.convert(value, typeDesc.getType()));
			}
		}

		return target;
	}

}

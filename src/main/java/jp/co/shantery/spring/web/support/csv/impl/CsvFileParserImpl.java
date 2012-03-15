/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.impl;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.shantery.spring.web.support.annotation.CsvColumn;
import jp.co.shantery.spring.web.support.csv.CsvFileParser;
import jp.co.shantery.spring.web.support.exception.IORuntimeException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

/**
 * {@link CsvFileParser}の実装クラスです。
 * 
 * @author m-namiki
 * 
 */
public class CsvFileParserImpl implements CsvFileParser {

	// ----------------------------------------------------------- [Properties]

	/** 型変換サービスです。 */
	private ConversionService conversionService;

	/** 読み込み対象ファイルです。 */
	private File file;

	// --------------------------------------------------------- [Constructors]

	/**
	 * コンストラクタです。
	 */
	public CsvFileParserImpl() {
		FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
		factoryBean.afterPropertiesSet();
		conversionService = factoryBean.getObject();
	}

	// ------------------------------------------------------- [Public methods]

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public <T> List<T> readAllLines(Class<T> clazz) {
		if (null == file) {
			throw new IllegalStateException("file isn't specified.");
		}
		List<T> list = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if ("".equals(line)) {
					break;
				}
				list.add(createBean(clazz, line));
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		return list;
	}

	@Override
	public <T> Iterator<T> iterator(Class<T> clazz) {
		return new CsvFileParserIterator<>(clazz);
	}

	// ------------------------------------------------------ [Private methods]

	/**
	 * 指定された行からインスタンスを作成します。
	 * 
	 * @param clazz
	 *            型
	 * @param line
	 *            行
	 * @return インスタンス
	 */
	private final <T> T createBean(Class<T> clazz, String line) {
		String[] array = line.split(",");

		T entity = BeanUtils.instantiate(clazz);
		BeanWrapper beanWrapper = PropertyAccessorFactory
				.forBeanPropertyAccess(entity);
		PropertyDescriptor[] propDescs = beanWrapper.getPropertyDescriptors();
		for (PropertyDescriptor propDesc : propDescs) {
			TypeDescriptor typeDesc = beanWrapper
					.getPropertyTypeDescriptor(propDesc.getName());
			CsvColumn col = (CsvColumn) typeDesc.getAnnotation(CsvColumn.class);
			if (null != col) {
				beanWrapper.setPropertyValue(
						propDesc.getName(),
						conversionService.convert(array[col.index()],
								typeDesc.getType()));
			}
		}
		return entity;
	}

	// ------------------------------------------------------ [Private classes]

	/**
	 * CSVファイル読み込み用のイテレータです。
	 * 
	 * @author m-namiki
	 * 
	 * @param <T>
	 *            型
	 */
	private class CsvFileParserIterator<T> implements Iterator<T> {

		private BufferedReader reader;
		private String line;
		private Class<T> type;

		private CsvFileParserIterator(Class<T> clazz) {
			type = clazz;
		}

		@Override
		public boolean hasNext() {
			try {
				if (null == reader) {
					reader = new BufferedReader(new FileReader(file));
				}
				line = reader.readLine();
				if (null == line) {
					IOUtils.closeQuietly(reader);
					return false;
				}

			} catch (IOException e) {
				throw new IORuntimeException(e);
			}
			return true;
		}

		@Override
		public T next() {
			if ("".equals(line)) {
				return null;
			}
			return createBean(type, line);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}

/**
 * 
 */
package jp.co.shantery.spring.web.support.csv.writer.impl;

import java.beans.PropertyDescriptor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jp.co.shantery.spring.web.support.annotation.CsvColumn;
import jp.co.shantery.spring.web.support.csv.writer.CsvFileWriter;
import jp.co.shantery.spring.web.support.exception.IORuntimeException;
import jp.co.shantery.spring.web.support.util.SWFieldUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

/**
 * {@link CsvFileWriter}の実装クラスです。
 * 
 * @author m-namiki
 * 
 */
@Component
public class DefaultCsvFileWriterImpl implements CsvFileWriter {

	/** 書き出し対象ファイルです。 */
	private File file;

	private BufferedWriter writer;

	private List<String> orderList;

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void write(Object obj) {
		try {
			if (null == writer) {
				writer = new BufferedWriter(new FileWriter(file));
			}
			if (null == orderList) {
				orderList = new ArrayList<>();
				setOutputOrder(obj);
			}

			StringBuilder buf = new StringBuilder();
			for (String columnName : orderList) {
				buf.append(getValue(obj, columnName) + ",");
			}
			buf.substring(0, buf.length() - 1);
			writer.append(buf.toString());
			writer.flush();

		} catch (IOException e) {
			throw new IORuntimeException(e);
		}

	}

	@Override
	public void close() {
		IOUtils.closeQuietly(writer);
	}

	@Override
	public void writeAll(Collection<?> collection) {
		if (null == file) {
			throw new IllegalStateException("file isn't specified.");
		}
		try {
			for (Object obj : collection) {
				write(obj);
			}
		} finally {
			close();
		}
	}

	/**
	 * 指定されたオブジェクトから出力順序を決めます。
	 * 
	 * @param obj
	 *            出力対象オブジェクト
	 */
	private void setOutputOrder(Object obj) {
		// 出力順序の設定
		BeanWrapper beanWrapper = PropertyAccessorFactory
				.forBeanPropertyAccess(obj);
		PropertyDescriptor[] propDescs = beanWrapper.getPropertyDescriptors();
		for (PropertyDescriptor propDesc : propDescs) {
			TypeDescriptor typeDesc = beanWrapper
					.getPropertyTypeDescriptor(propDesc.getName());
			CsvColumn col = (CsvColumn) typeDesc.getAnnotation(CsvColumn.class);
			if (null != col) {
				orderList.add(col.index(), propDesc.getName());
			}
		}
	}

	private String getValue(Object obj, String columnName) {
		return SWFieldUtils.get(
				SWFieldUtils.getField(obj.getClass(), columnName), obj)
				.toString();
	}
}

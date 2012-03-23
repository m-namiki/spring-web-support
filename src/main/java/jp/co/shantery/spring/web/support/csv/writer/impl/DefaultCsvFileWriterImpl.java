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

	@Override
	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public void write(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeAll(Collection<?> collection) {
		if (null == file) {
			throw new IllegalStateException("file isn't specified.");
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			List<String> orderList = new ArrayList<>();
			for (Object obj : collection) {
				// TODO 一回だけやって、後はキャッシュしておく
				BeanWrapper beanWrapper = PropertyAccessorFactory
						.forBeanPropertyAccess(obj);
				PropertyDescriptor[] propDescs = beanWrapper
						.getPropertyDescriptors();
				for (PropertyDescriptor propDesc : propDescs) {
					TypeDescriptor typeDesc = beanWrapper
							.getPropertyTypeDescriptor(propDesc.getName());
					CsvColumn col = (CsvColumn) typeDesc
							.getAnnotation(CsvColumn.class);
					if (null != col) {
						orderList.add(col.index(), propDesc.getName());
					}
				}
			}

			for (String propName : orderList) {
				System.out.println(propName);
			}
		} catch (IOException e) {
			IOUtils.closeQuietly(writer);
		}

	}

}

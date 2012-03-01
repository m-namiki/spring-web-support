/**
 * 
 */
package jp.co.shantery.spring.web.support.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Hiddenタグへの埋め込み対象を表すアノテーションです。
 * 
 * @author m-namiki
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface HiddenParam {

}

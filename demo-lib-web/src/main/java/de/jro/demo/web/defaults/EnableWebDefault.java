package de.jro.demo.web.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * Enables Swagger and Error Handler
 * 
 * @author Josef Rossa
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(WebDefaultConfig.class)
public @interface EnableWebDefault {

}

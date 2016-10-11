package de.jro.demo.web.defaults;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * Enables Error Handler
 * 
 * @author Josef Rossa
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ErrorHandlingConfig.class)
public @interface EnableErrorHandling {

}

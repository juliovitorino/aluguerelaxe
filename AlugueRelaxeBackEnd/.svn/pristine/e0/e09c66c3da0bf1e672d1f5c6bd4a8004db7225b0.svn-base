
package br.com.jcv.backend.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Obrigatorio {
	boolean notNull() default false;
	int tamanho();
	String[] dominio() default {};
}


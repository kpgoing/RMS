package org.sel.rms.exception.annoation;

import org.sel.rms.status.inter.Statusable;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by xubowei on 30/10/2016.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WithStatus {
    @AliasFor("code")
    Class<? extends Statusable> value() ;
}

package com.aas.samples.customerproducts.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.aas.samples.customerproducts.model.Person;


/**
 * Simple test to make sure that Bean Validation is working (useful when 
 * upgrading to a new version of Hibernate Validator/ Bean Validation).
 *
 * @author Amadeo Asco
 */
public class ValidatorTests {

    private Validator createValidator() {
        final LocalValidatorFactoryBean localValidatorFactoryBean 
        		= new LocalValidatorFactoryBean();

        localValidatorFactoryBean.afterPropertiesSet();

        return localValidatorFactoryBean;
    }

    @Test
    public void shouldNotValidateWhenFirstNameEmpty() {
        LocaleContextHolder.setLocale(Locale.ENGLISH);

        final Person person = new Person();

        person.setFirstName("");
        person.setLastName("smith");

        final Validator validator = createValidator();
        final Set<ConstraintViolation<Person>> constraintViolations 
        		= validator.validate(person);

        assertThat(constraintViolations.size()).isEqualTo(1);

        final ConstraintViolation<Person> violation 
        		= constraintViolations.iterator().next();

        assertThat(violation.getPropertyPath().toString()).isEqualTo("firstName");
        assertThat(violation.getMessage()).isEqualTo("may not be empty");
    }

}

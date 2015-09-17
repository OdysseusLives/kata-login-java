package loginkata.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class UserTest {
    public static Validator validator;
    public static User validUser;

    @BeforeClass
    public static void setUp() {
        validUser = new User("12345", "12e4567B");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreateAValidUser() throws Exception {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(validUser);

        assertThat(constraintViolations.size()).isEqualTo(0);
    }

}
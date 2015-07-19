package loginkata.models;

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
        validUser = new User("12345", "12345678");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreateAValidUser() throws Exception {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(validUser);

        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void shouldRequireAUsername() throws Exception {
        User user = new User(null, validUser.getPassword());

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("may not be null");
    }

    @Test
    public void shouldFailAUsernameUnderFiveCharacters() throws Exception {
        User user = new User("hats", validUser.getPassword());

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).contains("size must be between 5 and");
    }

    @Test
    public void shouldRequireAPassword() throws Exception {
        User user = new User(validUser.getUsername(), null);

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("may not be null");
    }

    @Test
    public void shouldFailAPAsswordUnderEightCharacters() throws Exception {
        User user = new User(validUser.getUsername(), "1234567");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.size()).isEqualTo(1);
        assertThat(constraintViolations.iterator().next().getMessage()).contains("size must be between 8 and");
    }

}
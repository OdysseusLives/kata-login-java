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
        validUser = new User("12345", "12e4567B");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldCreateAValidUser() throws Exception {
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(validUser);

        assertThat(constraintViolations.size()).isEqualTo(0);
    }

    @Test
    public void username_shouldBeRequired() throws Exception {
        User user = new User(null, validUser.getPassword());

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("may not be null");
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void username_shouldHaveFiveOrMoreCharacters() throws Exception {
        User user = new User("hats", validUser.getPassword());

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.iterator().next().getMessage()).contains("size must be between 5 and");
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_shouldBeRequired() throws Exception {
        User user = new User(validUser.getUsername(), null);

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.iterator().next().getMessage()).isEqualTo("may not be null");
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_shouldHaveEightOrMoreCharacters() throws Exception {
        User user = new User(validUser.getUsername(), "aA12345");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.iterator().next().getMessage()).contains("size must be between 8 and");
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_shouldContainALowerCaseLetter() throws Exception {
        User user = new User(validUser.getUsername(), "1234567983A");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.iterator().next().getMessage()).contains("must contain one lowercase letter.");
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

    @Test
    public void password_shouldContainAnUpperCaseLetter() throws Exception {
        User user = new User(validUser.getUsername(), "1234567983a");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        assertThat(constraintViolations.iterator().next().getMessage()).contains("must contain one uppercase letter.");
        assertThat(constraintViolations.size()).isEqualTo(1);
    }

}
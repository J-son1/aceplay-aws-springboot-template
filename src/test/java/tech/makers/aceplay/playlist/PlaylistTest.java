package tech.makers.aceplay.playlist;

import org.junit.jupiter.api.Test;
// import tech.makers.aceplay.playlist.Playlist;

import tech.makers.aceplay.user.User;

// import java.net.MalformedURLException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// https://www.youtube.com/watch?v=L4vkcgRnw2g&t=1099s
class PlaylistTest {
  
  private Validator validator;
  
  @Test
  void testConstructs() {
    User user = new User("username", "password");
    Playlist subject = new Playlist("Hello, world!", Set.of(), user);
    assertEquals("Hello, world!", subject.getName());
    assertEquals(Set.of(), subject.getTracks());
    assertEquals(null, subject.getId());
    assertEquals(true, subject.getCool());
  }

  @Test
  void testNameNotBlank() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    Playlist subject = new Playlist(" ", Set.of());
    Set<ConstraintViolation<Playlist>> violations = validator.validate(subject);
        assertFalse(violations.isEmpty());
  }

  @Test
  void testSetsCoolness() {
    User user = new User("username", "password");
    Playlist subject = new Playlist("Hello, world!", user);
    assertEquals(true, subject.getCool());

    subject.setCool(false);
    assertEquals(false, subject.getCool());
  }

  @Test
  void testToString() {
    User user = new User("username", "password");
    Playlist subject = new Playlist("Hello, world!", user);
    assertEquals(
        "Playlist[id=null name='Hello, world!']",
        subject.toString());
  }
}

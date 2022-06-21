package tech.makers.aceplay.playlist;

import org.junit.jupiter.api.Test;
// import tech.makers.aceplay.playlist.Playlist;

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
    Playlist subject = new Playlist("Hello, world!", Set.of());
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
    Playlist subject = new Playlist("Hello, world!");
    assertEquals(true, subject.getCool());

    subject.setCool(false);
    assertEquals(false, subject.getCool());
  }

  @Test
  void testToString() {
    Playlist subject = new Playlist("Hello, world!");
    assertEquals(
        "Playlist[id=null name='Hello, world!']",
        subject.toString());
  }
}


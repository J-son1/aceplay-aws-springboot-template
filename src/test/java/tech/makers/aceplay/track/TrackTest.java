package tech.makers.aceplay.track;

import org.junit.jupiter.api.Test;
// import tech.makers.aceplay.track.Track;

import tech.makers.aceplay.playlist.Playlist;

import java.net.MalformedURLException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// https://www.youtube.com/watch?v=L4vkcgRnw2g&t=798s
class TrackTest {

  private Validator validator;

  @Test
  void testConstructs() throws MalformedURLException {
    Track subject = new Track("Hello, world!", "Sarah", "https://example.org/track.mp3");
    assertEquals("Hello, world!", subject.getTitle());
    assertEquals("Sarah", subject.getArtist());
    assertEquals("https://example.org/track.mp3", subject.getPublicUrl().toString());
    assertEquals(null, subject.getId());
  }

  @Test
  void testToString() throws MalformedURLException {
    Track subject = new Track("Hello, world!", "Sarah", "https://example.org/track.mp3");
    assertEquals(
        "Track[id=null title='Hello, world!' artist='Sarah' publicUrl='https://example.org/track.mp3']",
        subject.toString());
  }

  @Test
  void testSetPublicUrl() throws MalformedURLException {
    Track subject = new Track();
    subject.setPublicUrl("https://example.org/track.mp3");
    assertEquals("https://example.org/track.mp3", subject.getPublicUrl().toString());
  }

  @Test
  void testTitleNotBlank() throws MalformedURLException {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    Track subject = new Track(" ", "TEST ARTIST", "http://TEST-URL.com");
    Set<ConstraintViolation<Track>> violations = validator.validate(subject);
    assertFalse(violations.isEmpty());

  }

  @Test
  void testArtistNotBlank() throws MalformedURLException {

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    Track subject = new Track("TEST TITLE", " ", "http://TEST-URL.com");
    Set<ConstraintViolation<Track>> violations = validator.validate(subject);
    assertFalse(violations.isEmpty());

  }

}

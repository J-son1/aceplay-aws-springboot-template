package tech.makers.aceplay.track;

import java.net.MalformedURLException;
import java.net.URL;

public class TrackDTO {
  private Long id;
  private String title;
  private String artist;
  private URL publicUrl;

  public TrackDTO() { }

  public TrackDTO(String title, String artist, URL publicUrl) {
    this.title = title;
    this.artist = artist;
    this.publicUrl = publicUrl;
  }

  public TrackDTO(String title, String artist, String publicUrl) throws MalformedURLException {
    this(title, artist, new URL(publicUrl));
  }
  
  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public URL getPublicUrl() {
    return publicUrl;
  }

  public void setPublicUrl(String publicUrl) throws MalformedURLException {
    this.publicUrl = new URL(publicUrl);
  }

  public void setPublicUrl(URL publicUrl) {
    this.publicUrl = publicUrl;
  }
}

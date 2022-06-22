package tech.makers.aceplay.playlist;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.user.User;

public class PlaylistDTO {
  Long id;

  @NotBlank(message = "Name should not be blank")
  String name;
  Boolean cool;
  Set<Track> tracks;
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public PlaylistDTO() {
  }

  public PlaylistDTO(String name, User user) {
    this(name, null, true, user);
  }

  public PlaylistDTO(String name, Set<Track> tracks, User user) {
    this(name, tracks, true, user);
  }

  public PlaylistDTO(String name, Set<Track> tracks, Boolean isCool, User user) {
    this.name = name;
    this.tracks = tracks;
    this.cool = isCool;
    this.user = user;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public Boolean getCool() {
    return this.cool;
  }

  public void setCool(Boolean isCool) {
    this.cool = isCool;
  }
}

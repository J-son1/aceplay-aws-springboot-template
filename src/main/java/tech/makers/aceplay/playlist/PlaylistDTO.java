package tech.makers.aceplay.playlist;

import java.util.Set;

import tech.makers.aceplay.track.Track;

public class PlaylistDTO {
  Long id;
  String name;
  Boolean cool;
  Set<Track> tracks;

  public PlaylistDTO() {
  }

  public PlaylistDTO(String name) {
    this(name, null, true);
  }

  public PlaylistDTO(String name, Set<Track> tracks) {
    this(name, tracks, true);
  }

  public PlaylistDTO(String name, Set<Track> tracks, Boolean isCool) {
    this.name = name;
    this.tracks = tracks;
    this.cool = isCool;
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

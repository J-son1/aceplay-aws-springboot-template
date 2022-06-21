package tech.makers.aceplay.playlist;

import com.fasterxml.jackson.annotation.JsonGetter;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.user.User;

import javax.persistence.*;
import java.util.Set;

// https://www.youtube.com/watch?v=vreyOZxdb5Y&t=448s
@Entity
public class Playlist {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private Boolean cool;

  @ManyToMany(fetch = FetchType.EAGER)
  private Set<Track> tracks;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Playlist() {
  }

  public Playlist(String name, User user) {
    this(name, null, true, user);
  }

  public Playlist(String name, Set<Track> tracks, User user) {
    this(name, tracks, true, user);
  }

  public Playlist(String name, Set<Track> tracks, Boolean isCool, User user) {
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

  @JsonGetter("tracks")
  public Set<Track> getTracks() {
    if (null == tracks) {
      return Set.of();
    }
    return tracks;
  }

  @Override
  public String toString() {
    return String.format("Playlist[id=%d name='%s']", id, name);
  }
}

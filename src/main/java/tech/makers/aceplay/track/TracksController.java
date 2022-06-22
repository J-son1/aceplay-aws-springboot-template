package tech.makers.aceplay.track;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import tech.makers.aceplay.user.User;
import tech.makers.aceplay.user.UserRepository;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.security.Principal;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=2410s
@RestController
public class TracksController {
  @Autowired
  private TrackRepository trackRepository;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/api/tracks")
  public Iterable<Track> index(Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    return trackRepository.findByUser(user);
  }

  @PostMapping("/api/tracks")
  public Track create(@RequestBody TrackDTO trackDTO, Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    Track track = new Track(null, null, null, user);
    BeanUtils.copyProperties(trackDTO, track);
    return trackRepository.save(track);
  }

  @PatchMapping("/api/tracks/{id}")
  public Track update(@PathVariable Long id, @RequestBody TrackDTO newTrack) {
    Track track = trackRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + id));
    track.setTitle(newTrack.getTitle());
    track.setArtist(newTrack.getArtist());
    trackRepository.save(track);
    return track;
  }

  @DeleteMapping("/api/tracks/{id}")
  public void delete(@PathVariable Long id) {
    Track track = trackRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + id));
    trackRepository.delete(track);
  }
}

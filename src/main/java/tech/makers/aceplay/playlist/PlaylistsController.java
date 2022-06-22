package tech.makers.aceplay.playlist;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tech.makers.aceplay.track.Track;
import tech.makers.aceplay.track.TrackRepository;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

// https://www.youtube.com/watch?v=vreyOZxdb5Y&t=0s
@RestController
public class PlaylistsController {
  @Autowired
  private PlaylistRepository playlistRepository;

  @Autowired
  private TrackRepository trackRepository;

  @GetMapping("/api/playlists")
  public Iterable<Playlist> playlists() {
    return playlistRepository.findAll();
  }

  @PostMapping("api/playlists")
  public Playlist create(@Valid @RequestBody PlaylistDTO playlistDTO) throws Exception {
    Playlist playlist = new Playlist();
    BeanUtils.copyProperties(playlistDTO, playlist);
    return playlistRepository.save(playlist);
  }

  @GetMapping("/api/playlists/cool")
  public Iterable<Playlist> coolPlaylists() {
    return playlistRepository.findAll();
  }

  @GetMapping("/api/playlists/uncool")
  public Iterable<Playlist> uncoolPlaylists() {
    return playlistRepository.findAll();
  }

  @GetMapping("/api/playlists/{id}")
  public Playlist get(@PathVariable Long id) {
    return playlistRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
  }

  @PutMapping("/api/playlists/{id}/tracks")
  public Track addTrack(@PathVariable Long id, @RequestBody TrackIdentifierDto trackIdentifierDto) {
    Playlist playlist = playlistRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "No playlist exists with id " + id));
    Track track = trackRepository.findById(trackIdentifierDto.getId())
        .orElseThrow(
            () -> new ResponseStatusException(NOT_FOUND, "No track exists with id " + trackIdentifierDto.getId()));
    playlist.getTracks().add(track);
    playlistRepository.save(playlist);
    return track;
  }

  @DeleteMapping(path = "/api/playlists/{id}")
  public void deleteTrack(@PathVariable Long id) {
    playlistRepository.deleteById(id);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  ResponseEntity<ConstraintViolationException> handleConstraintViolationException(ConstraintViolationException e) {
    return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
  }

}

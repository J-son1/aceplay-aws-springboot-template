package tech.makers.aceplay.playlist;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import tech.makers.aceplay.user.User;

// https://www.youtube.com/watch?v=vreyOZxdb5Y&t=343s
public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
  Playlist findFirstByOrderByIdAsc();

  List<Playlist> findByCoolEquals(Boolean isCool);

  List<Playlist> findByUser(User user, Sort sort);

  List<Playlist> findAllByUser(User user);
}

package niroj.movie.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import niroj.movie.models.MovieModel;

import java.util.List;

public interface MovieRepository extends MongoRepository<MovieModel, String> {
    List<MovieModel> findByIsTvShow(boolean isTvShow);
    List<MovieModel> findByTitleContainingIgnoreCase(String title);
    List<MovieModel> findByIsTvShowAndIsFeatured(boolean isTvShow, boolean isFeatured);
}

package niroj.movie.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import niroj.movie.models.MovieModel;
import niroj.movie.repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public MovieModel addMovie(MovieModel movie) {
        System.out.println("Movie: " + movie.getTitle() + " | isTvShow: " + movie.isTvShow());
        return movieRepository.save(movie);
    }

    public MovieModel updateMovie(String id, MovieModel updatedMovie) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(updatedMovie.getTitle());
            movie.setPrice(updatedMovie.getPrice());
            movie.setSynopsis(updatedMovie.getSynopsis());
            movie.setPoster(updatedMovie.getPoster());  // Updated to use the new poster field
            movie.setRentPrice(updatedMovie.getRentPrice());
            movie.setPurchasePrice(updatedMovie.getPurchasePrice());
            movie.setIsFeatured(updatedMovie.isFeatured());
            
            if (updatedMovie.isTvShow()) {
                movie.setSeasons(updatedMovie.getSeasons());
                movie.setDuration(0);
            } else {
                movie.setDuration(updatedMovie.getDuration());
                movie.setSeasons(0);
            }
            
            return movieRepository.save(movie);
        }).orElse(null);
    }

    public boolean deleteMovie(String id) {
        return movieRepository.findById(id).map(movie -> {
            movieRepository.delete(movie);
            return true;
        }).orElse(false);
    }

    public List<MovieModel> getAllMovies() {
        List<MovieModel> movies = movieRepository.findByIsTvShow(false);
        return movies;
    }

    public List<MovieModel> getAllTvShows() {
        return movieRepository.findByIsTvShow(true);
    }

    public List<MovieModel> searchByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<MovieModel> getFeaturedMovies() {
        return movieRepository.findByIsTvShowAndIsFeatured(false, true);
    }

    public List<MovieModel> getFeaturedTvShows() {
        return movieRepository.findByIsTvShowAndIsFeatured(true, true);
    }

    public Optional<MovieModel> getMovieById(String id) {
        return movieRepository.findById(id);
    }
}

package niroj.movie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import niroj.movie.models.MovieModel;
import niroj.movie.services.MovieService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieModel> addMovie(@RequestBody MovieModel movie) {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @GetMapping
    public ResponseEntity<List<MovieModel>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/tvshows")
    public ResponseEntity<List<MovieModel>> getAllTvShows() {
        return ResponseEntity.ok(movieService.getAllTvShows());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieModel>> searchMovies(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchByTitle(title));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<MovieModel>> getFeaturedMovies() {
        return ResponseEntity.ok(movieService.getFeaturedMovies());
    }

    @GetMapping("/featured/tvshows")
    public ResponseEntity<List<MovieModel>> getFeaturedTvShows() {
        return ResponseEntity.ok(movieService.getFeaturedTvShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieModel> getMovieById(@PathVariable String id) {
        Optional<MovieModel> movie = movieService.getMovieById(id);
        return movie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieModel> updateMovie(@PathVariable String id, @RequestBody MovieModel movie) {
        MovieModel updatedMovie = movieService.updateMovie(id, movie);
        return updatedMovie != null ? ResponseEntity.ok(updatedMovie) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        return movieService.deleteMovie(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
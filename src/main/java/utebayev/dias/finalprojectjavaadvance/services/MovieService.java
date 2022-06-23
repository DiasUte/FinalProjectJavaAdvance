package utebayev.dias.finalprojectjavaadvance.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import utebayev.dias.finalprojectjavaadvance.entities.Movie;
import utebayev.dias.finalprojectjavaadvance.repositories.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Iterable<Movie> showMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public Movie showEditMovieForm(Long id) {
        Movie movie = movieRepository.findById(id).get();
        return movie;
    }
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public boolean addNewMovie(Movie movie) {
        if(movieRepository.getMovieByName(movie.getName()) != null) {
            return false;
        }
        movie.setGenreId(1);
        movieRepository.save(movie);
        return true;
    }
}

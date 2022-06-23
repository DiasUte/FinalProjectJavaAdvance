package utebayev.dias.finalprojectjavaadvance.repositories;

import utebayev.dias.finalprojectjavaadvance.entities.Movie;
import utebayev.dias.finalprojectjavaadvance.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    Movie findById(int id);

    @Query("SELECT u FROM Movie u WHERE u.name = :name")
    public User getMovieByName(@Param("name") String name);
}

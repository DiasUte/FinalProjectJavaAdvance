package utebayev.dias.finalprojectjavaadvance.repositories;

import utebayev.dias.finalprojectjavaadvance.entities.TVShow;
import utebayev.dias.finalprojectjavaadvance.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TVShowRepository extends CrudRepository<TVShow, Long> {

    @Query("SELECT u FROM TVShow u WHERE u.name = :name")
    public User getShowByName(@Param("name") String name);

}

package utebayev.dias.finalprojectjavaadvance.repositories;

import utebayev.dias.finalprojectjavaadvance.entities.Serial;
import utebayev.dias.finalprojectjavaadvance.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SerialRepository extends CrudRepository<Serial, Long> {

    @Query("SELECT u FROM Serial u WHERE u.name = :name")
    public User getSerialByName(@Param("name") String name);
}

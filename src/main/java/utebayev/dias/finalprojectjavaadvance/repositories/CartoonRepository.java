package utebayev.dias.finalprojectjavaadvance.repositories;

import utebayev.dias.finalprojectjavaadvance.entities.Cartoon;
import utebayev.dias.finalprojectjavaadvance.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CartoonRepository extends CrudRepository<Cartoon, Long> {

    @Query("SELECT u FROM Cartoon u WHERE u.name = :name")
    public User getCartoonByName(@Param("name") String name);

}

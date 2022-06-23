package utebayev.dias.finalprojectjavaadvance.repositories;

import utebayev.dias.finalprojectjavaadvance.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}

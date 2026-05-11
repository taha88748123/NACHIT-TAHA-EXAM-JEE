package ma.nachit.taha.assurance_app.security.repositories;

import ma.nachit.taha.assurance_app.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {

    AppRole findByRoleName(String roleName);
}

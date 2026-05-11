package ma.nachit.taha.assurance_app.security.repositories;

import ma.nachit.taha.assurance_app.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}

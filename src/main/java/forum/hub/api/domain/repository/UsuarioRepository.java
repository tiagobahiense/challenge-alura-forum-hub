package forum.hub.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import forum.hub.api.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);
}

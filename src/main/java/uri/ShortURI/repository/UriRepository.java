package uri.ShortURI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uri.ShortURI.domain.Uri;

import java.sql.ResultSet;
import java.util.Optional;

public interface UriRepository extends JpaRepository<Uri,Long> { //Spring JPA를 사용하여 기본적인 기능 제공받음.
    Optional<Uri> findBychangeduri(String changeduri);
}

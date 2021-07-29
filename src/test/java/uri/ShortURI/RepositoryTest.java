package uri.ShortURI;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.repository.UriRepositoryImpl;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class RepositoryTest {
    @Autowired
    private EntityManager entityManager;

    UriRepositoryImpl uriRepository = new UriRepositoryImpl(entityManager);


}

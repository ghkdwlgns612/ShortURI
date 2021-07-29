package uri.ShortURI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import uri.ShortURI.repository.UriRepositoryImpl;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class RepositoryTest {
    @Autowired
    private EntityManager entityManager;

    //UriRepositoryImpl uriRepository = new UriRepositoryImpl(entityManager, getBuildUtils);


}

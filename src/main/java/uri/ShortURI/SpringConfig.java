package uri.ShortURI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uri.ShortURI.repository.UriRepository;
import uri.ShortURI.repository.UriRepositoryImpl;
import uri.ShortURI.service.UriService;
import uri.ShortURI.service.UriServiceImpl;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final EntityManager em;
    private final DataSource dataSource;

    public SpringConfig(EntityManager em, DataSource dataSource) {
        this.em = em;
        this.dataSource = dataSource;
    }

    @Bean
    public UriRepository uriRepository() {
        return new UriRepositoryImpl(em);
    }

    @Bean
    public UriService uriService() {
        return new UriServiceImpl(uriRepository());
    }
}

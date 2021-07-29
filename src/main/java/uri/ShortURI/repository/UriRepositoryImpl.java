package uri.ShortURI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.domain.UriRequestDto;
import uri.ShortURI.domain.UriResponsDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UriRepositoryImpl implements UriRepository{

    private final EntityManager em;

    @Autowired
    public UriRepositoryImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public Optional<Uri> findByOrigin(String originUri) {
        String jpql = "SELECT m FROM Uri m WHERE m.originuri = :orginuri ";
        List<Uri> result = em.createQuery(jpql,Uri.class).setParameter("orginuri",originUri).getResultList();
        return result.stream().findAny();
    }
    @Override
    public void save(UriRequestDto uriRequestDto) {
        Uri uri = Uri.builder()
                .id(uriRequestDto.getId())
                .originuri(uriRequestDto.getOriginuri())
                .changeduri(uriRequestDto.getChangeduri())
                .build();
        em.persist(uri);
    }
}

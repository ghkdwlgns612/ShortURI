package uri.ShortURI.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uri.ShortURI.domain.Uri;
import uri.ShortURI.controller.uri.dto.uri.UriRequestDto;
import uri.ShortURI.utils.GetBuildUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UriRepositoryImpl implements UriRepository{

    private final EntityManager em;
    private final GetBuildUtils getBuildUtils;

    @Autowired
    public UriRepositoryImpl(EntityManager em, GetBuildUtils getBuildUtils) {
        this.em = em;
        this.getBuildUtils = getBuildUtils;
    }
    @Override
    public Optional<Uri> findByOrigin(String originUri) {
        String jpql = "SELECT m FROM Uri m WHERE m.originuri = :orginuri ";
        List<Uri> result = em.createQuery(jpql,Uri.class).setParameter("orginuri",originUri).getResultList();
        return result.stream().findAny();
    }
    @Override
    public void save(UriRequestDto uriRequestDto) {
        Uri uri = getBuildUtils.getBuildUri(uriRequestDto);
        em.persist(uri);
    }
}

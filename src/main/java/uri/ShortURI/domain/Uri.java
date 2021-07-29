package uri.ShortURI.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "URISTORE")
@Getter
public class Uri {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originuri;
    @Column(nullable = false)
    private String changeduri;

    @Builder
    public Uri(Long id, String originuri, String changeduri) {
        this.id = id;
        this.originuri = originuri;
        this.changeduri = changeduri;
    }
}

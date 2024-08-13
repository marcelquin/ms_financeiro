package App.repository;



import App.Entity.RelatorioMensalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelatorioMensalRepository extends JpaRepository<RelatorioMensalEntity,Long> {

    boolean existsBydataReferencia(String dataReferencia);

    Optional<RelatorioMensalEntity> findBydataReferencia(String dataReferencia);
}

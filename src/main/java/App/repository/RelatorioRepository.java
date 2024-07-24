package App.repository;

import App.Entity.RelatorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioEntity,Long> {

    boolean existsBymesReferencia(int mesReferencia);

    Optional<RelatorioEntity> findBymesReferencia(int mesReferencia);

    Optional<RelatorioEntity> findByanoReferencia(int anoReferencia);
}

package App.repository;


import App.Entity.BoletoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends JpaRepository<BoletoEntity,Long> {
}

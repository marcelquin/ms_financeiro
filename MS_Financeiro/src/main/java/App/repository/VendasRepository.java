package App.repository;




import App.Entity.VendasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<VendasEntity,Long> {
}

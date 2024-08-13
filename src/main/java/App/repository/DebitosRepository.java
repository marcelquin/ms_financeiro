package App.repository;




import App.Entity.DebitosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitosRepository extends JpaRepository<DebitosEntity,Long> {
}

package ac.in.cutm.erp.std_input.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac.in.cutm.erp.std_input.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long>{

}

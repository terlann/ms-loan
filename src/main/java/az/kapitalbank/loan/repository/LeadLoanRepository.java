package az.kapitalbank.loan.repository;

import az.kapitalbank.loan.constants.Status;
import az.kapitalbank.loan.entity.LeadLoanEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadLoanRepository extends JpaRepository<LeadLoanEntity, String> {
    List<LeadLoanEntity> findBySourceAndStatusIsNot(String source, Status status);
}

package az.kapitalbank.loan.repository;

import az.kapitalbank.loan.entity.LeadLoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadLoanRepository extends JpaRepository<LeadLoanEntity, Long> {
}

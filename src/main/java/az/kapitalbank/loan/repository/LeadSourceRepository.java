package az.kapitalbank.loan.repository;

import az.kapitalbank.loan.entity.LeadLoanEntity;
import az.kapitalbank.loan.entity.LeadSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadSourceRepository extends JpaRepository<LeadSourceEntity,String> {
}

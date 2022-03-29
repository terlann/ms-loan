package az.kapitalbank.loan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = LeadSourceEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadSourceEntity {

    static final String TABLE_NAME = "LEAD_SOURCE";

    @Id
    String code;
    String name;
    boolean status;
}

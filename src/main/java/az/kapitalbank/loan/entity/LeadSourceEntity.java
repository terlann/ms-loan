package az.kapitalbank.loan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = LeadSourceEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LeadSourceEntity {

    static final String TABLE_NAME = "LEAD_SOURCE";

    @Id
    String code;
    String name;
    boolean status;
}

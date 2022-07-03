package az.express.payday.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
    @SequenceGenerator(name = "COMPANY_SEQ", allocationSize = 1, sequenceName = "COMPANY_SEQ")
    private Long id;
    private String username;
    private String password;
    private String email;
    private Double balance;
    private String token;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active;


}

package id.co.indivara.jdt12.minproBank.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trx_saldo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrxSaldo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "idsaldo")
    private String idSaldo;

    @Column(name = "akunId")
    private String  akunId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "akunId", updatable = false, insertable = false)
    private MstAkun mstAkun;
    @Column(name = "saldo")
    private BigDecimal saldo;
}

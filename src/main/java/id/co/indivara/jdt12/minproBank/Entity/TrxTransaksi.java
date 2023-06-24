package id.co.indivara.jdt12.minproBank.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "trx_Transaksi")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TrxTransaksi {
    @Id
    @Column(name = "id_transaksi")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String transactionId;

    @Column(name = "akunId")
    private String akunId;
    @JoinColumn(name = "akunId", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private MstAkun akun;

    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "noRekening")
    private String noRekening;


    @Column(name = "tanggal_Transaksi") //tanggal pembuatan transaksi
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant tanggalTransaksi;
    @Column(name = "jumlah")
    private BigDecimal jumlah;

    @Column(name="tipe_transaksi")
    @Enumerated(EnumType.STRING)
    private EnumTransaksi tipeTransaksi;

    public enum EnumTransaksi {
        SETOR ("SETOR"),
        TARIKTUNAI("TARIKTUNAI"),
        TRANSFER("TRANSFER");
        private String text;
        EnumTransaksi(String text) {this.text=text;}
        public String getText() {return text;}
    }
}

package id.co.indivara.jdt12.minproBank.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="mst_akun")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstAkun {
    @Id
    @Column(name = "akunId") //buat kalau nama d db sama nama d sini beda biar nyamain //di db
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String akunId; //di java

    @Column(name = "idPelanggan")
    private String idPelanggan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPelanggan",insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE) //buat menghapus di akun ini saja
    @JsonIgnore
    private MstPelanggan pelanggan;

    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column (name = "no_rekening")
    private String noRekening;
    @Column (name = "pin")
    private String pin;
}

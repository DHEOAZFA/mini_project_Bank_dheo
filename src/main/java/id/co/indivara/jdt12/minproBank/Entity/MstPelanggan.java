package id.co.indivara.jdt12.minproBank.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name= "mst_customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MstPelanggan {
    @Id
    @Column(name = "idPelanggan")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String idPelanggan;

    @Column(name = "nama_nasabah")
    private String namaNasabah;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "no_ktp")
    private String noKtp;
    @Column(name = "no_telp")
    private String noTelp;
    @Column(name = "email")
    private String email;
    @Column(name = "alamat")
    private String alamat;
}

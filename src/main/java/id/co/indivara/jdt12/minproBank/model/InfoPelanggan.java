package id.co.indivara.jdt12.minproBank.model;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InfoPelanggan {
    private MstPelanggan mstPelanggan;
    private List<MstAkun> mstAkunsList;
}

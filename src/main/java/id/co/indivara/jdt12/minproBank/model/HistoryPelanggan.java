package id.co.indivara.jdt12.minproBank.model;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.TrxTransaksi;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HistoryPelanggan {
private MstAkun mstAkun;
private List<TrxTransaksi> trxTransaksis;
}

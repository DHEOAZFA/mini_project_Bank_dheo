package id.co.indivara.jdt12.minproBank.service;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import id.co.indivara.jdt12.minproBank.Entity.TrxSaldo;
import id.co.indivara.jdt12.minproBank.Entity.TrxTransaksi;
import id.co.indivara.jdt12.minproBank.model.HistoryPelanggan;
import id.co.indivara.jdt12.minproBank.model.InfoPelanggan;
import id.co.indivara.jdt12.minproBank.model.SimpanAkun;
import id.co.indivara.jdt12.minproBank.repo.MstAkunRepository;
import id.co.indivara.jdt12.minproBank.repo.MstPelangganRepository;
import id.co.indivara.jdt12.minproBank.repo.TrxSaldoRepository;
import id.co.indivara.jdt12.minproBank.repo.TrxTransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MstAkunService {
@Autowired
    MstAkunRepository mstAkunRepository;
@Autowired
    TrxSaldoRepository trxSaldoRepository;
@Autowired
    MstPelangganRepository mstPelangganRepository;
@Autowired
    TrxTransaksiRepository trxTransaksiRepository;
    public List<MstAkun> getAllAccount(){
        return (List<MstAkun>) mstAkunRepository.findAll();
    }
    public Boolean setAccount (SimpanAkun simpanAkun){
        MstPelanggan pelanggan = (MstPelanggan) mstPelangganRepository.findById(simpanAkun.getIdPelanggan()).get();

        if (pelanggan != null){
            MstAkun act = mstAkunRepository.save(MstAkun.builder().
                    noRekening(String.valueOf(simpanAkun.getNoRekening())).
                    idPelanggan(pelanggan.getIdPelanggan()).
                            pelanggan(pelanggan).
                    pin(String.valueOf(simpanAkun.getPin())).
                    build());
            TrxSaldo trxSaldo = new TrxSaldo();

            trxSaldo.setAkunId(act.getAkunId());
            trxSaldo.setMstAkun(act);
            trxSaldo.setSaldo(BigDecimal.ZERO);
            trxSaldoRepository.save(trxSaldo);
            return true;
        }
        return false;
}
    public HistoryPelanggan historyPelanggan(String akunId) throws Exception {
        MstAkun akun = mstAkunRepository.findById(akunId).orElseThrow(()-> new Exception("pelanggan Error"));
        List<TrxTransaksi> trx = trxTransaksiRepository.
                findAllByTransaksiId(akun);
        return new HistoryPelanggan(akun,trx);
    }

}

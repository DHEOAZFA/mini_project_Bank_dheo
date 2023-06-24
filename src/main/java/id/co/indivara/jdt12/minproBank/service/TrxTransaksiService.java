package id.co.indivara.jdt12.minproBank.service;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.TrxSaldo;
import id.co.indivara.jdt12.minproBank.Entity.TrxTransaksi;
import id.co.indivara.jdt12.minproBank.repo.MstAkunRepository;
import id.co.indivara.jdt12.minproBank.repo.TrxSaldoRepository;
import id.co.indivara.jdt12.minproBank.repo.TrxTransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;

@Service
public class TrxTransaksiService {
@Autowired
    TrxTransaksiRepository trxTransaksiRepository;
@Autowired
    TrxSaldoRepository trxSaldoRepository;
@Autowired
    MstAkunRepository mstAkunRepository;
    @Transactional
    public TrxTransaksi buatSetor (TrxTransaksi akun)throws Exception {
        MstAkun hasil = mstAkunRepository.findById(akun.getAkunId()).orElseThrow(() -> new Exception("account tidak ditemukan"));
        TrxSaldo saldo = trxSaldoRepository.findById(akun.getAkunId()).orElseThrow(() -> new Exception("account balance tidak ditemukan"));
        akun.setAkun(hasil);
        akun.setTipeTransaksi(TrxTransaksi.EnumTransaksi.SETOR);
        akun.setTanggalTransaksi(Instant.now());
        trxTransaksiRepository.save(akun);

        saldo.setSaldo(saldo.getSaldo().add(akun.getJumlah()));
        trxSaldoRepository.save(saldo);
        return trxTransaksiRepository.save(akun);
    }
}

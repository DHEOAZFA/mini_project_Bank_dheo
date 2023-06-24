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
import java.math.BigDecimal;
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
        MstAkun hasil = mstAkunRepository.findById(akun.getAkunId()).orElseThrow(() -> new Exception("akun tidak ditemukan"));
        TrxSaldo saldo = trxSaldoRepository.findByAkunId(akun.getAkunId()).orElseThrow(() -> new Exception("saldo tidak ditemukan"));
        akun.setAkun(hasil);
        akun.setTipeTransaksi(TrxTransaksi.EnumTransaksi.SETOR);
        akun.setTanggalTransaksi(Instant.now());
        trxTransaksiRepository.save(akun);

        saldo.setSaldo(saldo.getSaldo().add(akun.getJumlah()));
        trxSaldoRepository.save(saldo);
        return trxTransaksiRepository.save(akun);
    }

    @Transactional
    public TrxTransaksi tarikTunai (TrxTransaksi akun)throws Exception {
        MstAkun hasil = mstAkunRepository.findById(akun.getAkunId()).orElseThrow(() -> new Exception("account tidak ditemukan"));
        TrxSaldo trxSaldo = trxSaldoRepository.findByAkunId(akun.getAkunId()).orElseThrow(() -> new Exception("account balance tidak ditemukan"));
        BigDecimal uangkurang= trxSaldo.getSaldo().subtract(akun.getJumlah());
       if(uangkurang.compareTo(BigDecimal.ZERO)<0){
           throw new Exception("maaf anda uang kurang");
       }
        akun.getTipeTransaksi();
        akun.setAkun(hasil);
        akun.setTipeTransaksi(TrxTransaksi.EnumTransaksi.TARIKTUNAI);
        akun.setTanggalTransaksi(Instant.now());
        trxTransaksiRepository.save(akun);

        trxSaldo.setSaldo(uangkurang);
        trxSaldoRepository.save(trxSaldo);
        return trxTransaksiRepository.save(akun);
    }

    @Transactional
    public TrxTransaksi transfer (TrxTransaksi akun)throws Exception {
        MstAkun hasil = mstAkunRepository.findById(akun.getAkunId()).orElseThrow(() -> new Exception("account tidak ditemukan"));
        TrxSaldo trxSaldo = trxSaldoRepository.findByAkunId(akun.getAkunId()).orElseThrow(() -> new Exception("account balance tidak ditemukan"));
        MstAkun akunTujuan= mstAkunRepository.findByNoRekening(hasil.getNoRekening()).orElseThrow(()->new Exception("tujuan tidak ditemukan"));
        TrxSaldo akunSaldoTujuan= trxSaldoRepository.findByAkunId(akunTujuan.getNoRekening()).orElseThrow(()->new Exception("saldo tidak ada"));
        BigDecimal uangKurang= trxSaldo.getSaldo().subtract(akun.getJumlah());
        if(uangKurang.compareTo(BigDecimal.ZERO)<0){
            throw new Exception("uangnya kurang");
        }
        BigDecimal uangTujuan=akunSaldoTujuan.getSaldo().add(akun.getJumlah());
        akun.setAkun(hasil);
        akun.setTipeTransaksi(TrxTransaksi.EnumTransaksi.TRANSFER);
        akun.setTanggalTransaksi(Instant.now());
        trxTransaksiRepository.save(akun);
        trxSaldo.setSaldo(trxSaldo.getSaldo().min(akun.getJumlah()));
        akunSaldoTujuan.setSaldo(uangTujuan);
        trxSaldoRepository.save(akunSaldoTujuan);
        return trxTransaksiRepository.save(akun);
    }
}


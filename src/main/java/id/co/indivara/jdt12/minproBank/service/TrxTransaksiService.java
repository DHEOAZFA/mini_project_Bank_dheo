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
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TrxTransaksiService {

@Autowired
    TrxTransaksiRepository trxTransaksiRepository;

@Autowired
    TrxSaldoRepository trxSaldoRepository;

@Autowired
    MstAkunRepository mstAkunRepository;
    public List<TrxTransaksi> getAlltransaksi(){
        return (List<TrxTransaksi>) trxTransaksiRepository.findAll();
    }
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
        TrxSaldo trxSaldo = trxSaldoRepository.findByMstAkun(hasil).orElseThrow(() -> new Exception("account balance tidak ditemukan"));
        BigDecimal uangkurang= trxSaldo.getSaldo().subtract(akun.getJumlah());
       if(uangkurang.compareTo(BigDecimal.ZERO)<0){
           throw new Exception("maaf uang anda kurang");
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
    public TrxTransaksi transfer(TrxTransaksi akun) {
        MstAkun hasil = mstAkunRepository.findById(akun.getAkunId())
                .orElseThrow(() -> new NoSuchElementException("Akun tidak ditemukan"));

        TrxSaldo trxSaldo = trxSaldoRepository.findByMstAkun(hasil)
                .orElseThrow(() -> new NoSuchElementException("Saldo akun tidak ditemukan"));

        MstAkun akunTujuan = mstAkunRepository.findByNoRekening(akun.getNoRekening())
                .orElseThrow(() -> new NoSuchElementException("Akun tujuan tidak ditemukan"));

        TrxSaldo saldotujuan = trxSaldoRepository.findByMstAkun(akunTujuan)
                .orElseThrow(() -> new NoSuchElementException("Saldo akun tujuan tidak ditemukan"));

        BigDecimal gadaduit = trxSaldo.getSaldo().subtract(akun.getJumlah());
        if (gadaduit.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo akun tidak mencukupi");
        }

        BigDecimal uangtujuan = saldotujuan.getSaldo().add(akun.getJumlah());

        TrxTransaksi newTrxTransaksi = new TrxTransaksi();
        newTrxTransaksi.setAkun(hasil);
        newTrxTransaksi.setTipeTransaksi(TrxTransaksi.EnumTransaksi.TRANSFER);
        newTrxTransaksi.setTanggalTransaksi(Instant.now());
        trxTransaksiRepository.save(newTrxTransaksi);

        trxSaldo.setSaldo(gadaduit);
        trxSaldoRepository.save(trxSaldo);

        saldotujuan.setSaldo(uangtujuan);
        trxSaldoRepository.save(saldotujuan);

        return newTrxTransaksi;
    }

}


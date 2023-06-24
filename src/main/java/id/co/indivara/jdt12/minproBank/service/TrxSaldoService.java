package id.co.indivara.jdt12.minproBank.service;

import id.co.indivara.jdt12.minproBank.Entity.TrxSaldo;
import id.co.indivara.jdt12.minproBank.repo.TrxSaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TrxSaldoService {
@Autowired
static TrxSaldoRepository trxSaldoRepository;
public static BigDecimal cekSaldo(String akunId){
    TrxSaldo trxSaldo=trxSaldoRepository.findById(akunId).orElse(null);
return trxSaldo.getSaldo();
}
}

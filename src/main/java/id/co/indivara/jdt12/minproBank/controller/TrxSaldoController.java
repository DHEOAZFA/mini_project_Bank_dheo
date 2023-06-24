package id.co.indivara.jdt12.minproBank.controller;

import id.co.indivara.jdt12.minproBank.Entity.TrxSaldo;
import id.co.indivara.jdt12.minproBank.repo.TrxSaldoRepository;
import id.co.indivara.jdt12.minproBank.service.TrxSaldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrxSaldoController {
@Autowired
    TrxSaldoService trxSaldoService;
@Autowired
    TrxSaldoRepository trxSaldoRepository;
@GetMapping("/saldoById")
    public TrxSaldo findByidSaldo(@PathVariable("idSaldo")String idSaldo){
    return trxSaldoRepository.findById(idSaldo).get();
}
}

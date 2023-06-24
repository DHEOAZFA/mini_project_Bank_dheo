package id.co.indivara.jdt12.minproBank.controller;

import id.co.indivara.jdt12.minproBank.Entity.TrxTransaksi;
import id.co.indivara.jdt12.minproBank.service.TrxTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrxTransaksiController {
@Autowired
    TrxTransaksiService trxTransaksiService;
@PostMapping("/Setor")
    public TrxTransaksi buatSetor(@RequestBody TrxTransaksi trxTransaksi)throws Exception{
    return trxTransaksiService.buatSetor(trxTransaksi);
}
}

package id.co.indivara.jdt12.minproBank.controller;

import id.co.indivara.jdt12.minproBank.Entity.TrxTransaksi;
import id.co.indivara.jdt12.minproBank.service.TrxTransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrxTransaksiController {
@Autowired
    TrxTransaksiService trxTransaksiService;
@PostMapping("/Setor")
    public TrxTransaksi buatSetor(@RequestBody TrxTransaksi trxTransaksi)throws Exception{
    return trxTransaksiService.buatSetor(trxTransaksi);
}
    @PostMapping("/tarikTunai")
    public TrxTransaksi buatTarikTunai(@RequestBody TrxTransaksi akun) throws Exception{
        return trxTransaksiService.tarikTunai(akun);
    }

    @PostMapping("/transfer")
    public TrxTransaksi saveTransfer(@RequestBody TrxTransaksi akun) throws Exception{
        return trxTransaksiService.transfer(akun);
    }
    @GetMapping("/transaksi")
    public List<TrxTransaksi> getAlltransaksi(){
        return trxTransaksiService.getAlltransaksi();
    }

}

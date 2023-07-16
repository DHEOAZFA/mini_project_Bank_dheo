package id.co.indivara.jdt12.minproBank.controller;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.model.HistoryPelanggan;
import id.co.indivara.jdt12.minproBank.model.SimpanAkun;
import id.co.indivara.jdt12.minproBank.service.MstAkunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController
    public class MstAkunController {
        @Autowired
        MstAkunService mstAkunService;

        @PostMapping("/simpanAkun")
        public Boolean simpanAkun(@RequestBody SimpanAkun simpanAkun){
            Boolean hasil = mstAkunService.setAccount(simpanAkun);
            return hasil;
        }

        @GetMapping("/historytransaction/{akunid}")
        public HistoryPelanggan gethistory (@PathVariable("akunid") String id) throws Exception {
            return mstAkunService.historyPelanggan(id);
        }
}

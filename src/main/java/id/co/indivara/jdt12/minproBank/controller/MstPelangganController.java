package id.co.indivara.jdt12.minproBank.controller;

import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import id.co.indivara.jdt12.minproBank.model.InfoPelanggan;
import id.co.indivara.jdt12.minproBank.service.MstPelangganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MstPelangganController {
@Autowired
    MstPelangganService mstPelangganService;
@PostMapping("/simpanPelanggan")
public MstPelanggan simpanPelanggan(@RequestBody MstPelanggan pelanggan){ return mstPelangganService.buatPelanggan(pelanggan);}

    @GetMapping("/pelanggan/{idPelanggan}")
    public InfoPelanggan getPelanggan(@PathVariable("idPelanggan") String id)throws Exception{
        return mstPelangganService.detailPelanggan(id);
    }
}

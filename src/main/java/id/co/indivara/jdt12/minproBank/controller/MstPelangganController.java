package id.co.indivara.jdt12.minproBank.controller;

import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import id.co.indivara.jdt12.minproBank.service.MstPelangganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MstPelangganController {
@Autowired
    MstPelangganService mstPelangganService;
@PostMapping("/simpanPelanggan")
public MstPelanggan simpanPelanggan(@RequestBody MstPelanggan pelanggan){ return mstPelangganService.buatPelanggan(pelanggan);}
    @GetMapping("/pelanggan")
    public List<MstPelanggan> getAllPelanggan(){return mstPelangganService.getAllCustomer();}
}

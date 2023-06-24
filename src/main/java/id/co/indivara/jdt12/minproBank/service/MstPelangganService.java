package id.co.indivara.jdt12.minproBank.service;

import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import id.co.indivara.jdt12.minproBank.repo.MstPelangganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstPelangganService {
    @Autowired
    MstPelangganRepository mstPelangganRepository;
    public List<MstPelanggan> getAllCustomer() {
        return mstPelangganRepository.findAll();
    }
    public MstPelanggan buatPelanggan(MstPelanggan pelanggan){
        return mstPelangganRepository.save(pelanggan);
    }
}

package id.co.indivara.jdt12.minproBank.service;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import id.co.indivara.jdt12.minproBank.model.InfoPelanggan;
import id.co.indivara.jdt12.minproBank.repo.MstAkunRepository;
import id.co.indivara.jdt12.minproBank.repo.MstPelangganRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MstPelangganService {
    @Autowired
    MstPelangganRepository mstPelangganRepository;
    @Autowired
    MstAkunRepository mstAkunRepository;

    public List<MstPelanggan> getAllCustomer() {
        return mstPelangganRepository.findAll();
    }

    public MstPelanggan buatPelanggan(MstPelanggan pelanggan) {
        return mstPelangganRepository.save(pelanggan);
    }

    public InfoPelanggan detailPelanggan(String idPelanggan) throws Exception {
        MstPelanggan pelanggan = mstPelangganRepository.findById(idPelanggan).orElseThrow(()-> new Exception("pelanggan Error"));
        List<MstAkun> akuns = mstAkunRepository.findAllByPelanggan(pelanggan);
        return new InfoPelanggan(pelanggan,akuns);
    }
}

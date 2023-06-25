package id.co.indivara.jdt12.minproBank.repo;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import id.co.indivara.jdt12.minproBank.Entity.TrxTransaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrxTransaksiRepository extends JpaRepository<TrxTransaksi, String> {
    List<TrxTransaksi> findAllByTransaction (MstAkun mstAkun);
}

package id.co.indivara.jdt12.minproBank.repo;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.MstPelanggan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MstAkunRepository extends JpaRepository<MstAkun,String> {
    Optional<MstAkun> findByNoRekening (String noRekening);
    List<MstAkun> findAllByPelanggan (MstPelanggan pelanggan);
}

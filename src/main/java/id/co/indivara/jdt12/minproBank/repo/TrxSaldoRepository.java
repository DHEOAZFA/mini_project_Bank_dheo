package id.co.indivara.jdt12.minproBank.repo;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import id.co.indivara.jdt12.minproBank.Entity.TrxSaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrxSaldoRepository extends JpaRepository<TrxSaldo, String> {
    Optional<TrxSaldo> findByAkunId(String akunid);
    Optional<TrxSaldo> findByMstAkun(MstAkun mstAkun);
}

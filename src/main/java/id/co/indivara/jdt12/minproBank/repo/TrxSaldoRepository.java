package id.co.indivara.jdt12.minproBank.repo;

import id.co.indivara.jdt12.minproBank.Entity.TrxSaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrxSaldoRepository extends JpaRepository<TrxSaldo, String> {
    Optional<TrxSaldo> findByIdSaldo(String idSaldo);
    Optional<TrxSaldo> findByAkunId(String akunId);
}

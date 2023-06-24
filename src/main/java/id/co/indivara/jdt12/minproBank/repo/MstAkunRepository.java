package id.co.indivara.jdt12.minproBank.repo;

import id.co.indivara.jdt12.minproBank.Entity.MstAkun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MstAkunRepository extends JpaRepository<MstAkun,String> {

}

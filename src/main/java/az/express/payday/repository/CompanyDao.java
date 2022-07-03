package az.express.payday.repository;

import az.express.payday.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends CrudRepository<Company,Long> {

    Company findCompanyByUsernameAndPasswordAndActive(String username, String password, Integer active);

    Company findCompanyByIdAndTokenAndActive(Long userId, String token, Integer active);

    Company findCompanyByUsernameAndEmailAndActive(String username,String email,Integer active);
}

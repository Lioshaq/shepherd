package md.mi.repository;

import java.util.UUID;

import md.mi.domain.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    //@Query("select a from Account a where a.userid = ?1")
    public Account findByUserid(UUID userid);
}

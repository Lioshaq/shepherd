package md.mi.repository;

import md.mi.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, String> {

    //@Query("SELECT User FROM public.users u WHERE u.username = ?1 ")
    public User findByUsername(String username);



}

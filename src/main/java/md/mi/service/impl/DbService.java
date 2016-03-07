package md.mi.service.impl;

import java.util.UUID;

import md.mi.domain.entity.Account;
import md.mi.domain.entity.User;
import md.mi.model.factory.AuthUserFactory;
import md.mi.repository.AccountRepository;
import md.mi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("dbService")
public class DbService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return AuthUserFactory.create(user); 
        }
    }

    public User saveAndFlush(User user){
        User result = userRepository.saveAndFlush(user);
        return result;
    }

    public Account saveAndFlushAccount(Account account){
        return accountRepo.saveAndFlush(account);
    }

    public Account findAccountByUserid(UUID userid){
        return accountRepo.findByUserid(userid);
    }

    public void saveAccount(Account account) {
        accountRepo.saveAndFlush(account);
    }

    //@Transactional
    public void makeTransfer(Account from, Account to) {
        saveAndFlushAccount(from);
        saveAndFlushAccount(to);
    }

}

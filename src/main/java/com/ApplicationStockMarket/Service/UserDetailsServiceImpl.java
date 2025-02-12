package com.ApplicationStockMarket.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.ApplicationStockMarket.Entity.StockUser;
import com.ApplicationStockMarket.Entity.UserPrincipal;
import com.ApplicationStockMarket.Repository.StockUserRepository;

@Service
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StockUserRepository stockUserserRepository;


    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        StockUser user = stockUserserRepository.findByUserName(username);
        if (user != null) {
        	return new UserPrincipal(user);
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
    
    
    
    
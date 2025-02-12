package com.ApplicationStockMarket.Entity;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	StockUser user;
	
	public UserPrincipal(StockUser user) {
		this.user=user;
	}

	@Override

	public Collection<? extends GrantedAuthority> getAuthorities() {
	    // Assuming 'user' is an object with a 'getRoles()' method 
	    // that returns a Collection<String> of roles
       List<String> userRoles=user.getRoles();
       if(userRoles!=null) {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    for (String role : user.getRoles()) {
	        authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;}
       else {
    	   return Collections.singleton(new SimpleGrantedAuthority("Admin"));
       }
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	
}

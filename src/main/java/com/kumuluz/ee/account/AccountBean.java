package com.kumuluz.ee.account;


import com.kumuluz.ee.account.configuration.RestProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AccountBean {

    @Inject
    private RestProperties restProperties;

    public boolean addAccount(Account a) {
        
		//System.out.println(restProperties.isRegistrationEnabled());
		
		if (restProperties.isRegistrationEnabled()) {
            Database.addAccounts(a);
            return true;
        } else {
            return false;
        }
    }

}

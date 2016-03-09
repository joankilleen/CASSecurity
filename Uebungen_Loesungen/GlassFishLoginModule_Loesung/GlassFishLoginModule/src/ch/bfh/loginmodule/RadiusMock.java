/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.loginmodule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author pitman
 */
public class RadiusMock implements RadiusAuthentication {

    private static HashMap<String, String> userlist = new HashMap<String, String>();

    public RadiusMock() {
        //mock -> stativ user
        userlist.put("user1", "12345678");
        userlist.put("user2", "23456789");
        userlist.put("user3", "34567891");
        userlist.put("admin", "12345678");
    }

    @Override
    public boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");

        String radiusNumber = password.substring(0, 4);
        String radiusPw = password.substring(4);
        String pwdList = userlist.get(username);
        
        String radiusDate = formatter.format(new Date(System.currentTimeMillis()));
        System.out.println(username + "/" + password + ":->" +  radiusNumber + "/" + radiusDate + "/" + radiusPw);
        
        if ((pwdList != null) && (radiusPw.equals(pwdList))) {
            //PW is OK
            if (radiusDate.equals(radiusNumber)) {
                isAuthenticated = true;
            }
        }
        return isAuthenticated;
    }

}

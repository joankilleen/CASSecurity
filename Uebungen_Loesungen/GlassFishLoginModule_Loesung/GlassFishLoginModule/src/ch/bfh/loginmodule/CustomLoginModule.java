package ch.bfh.loginmodule;

import com.sun.appserv.security.AppservPasswordLoginModule;
import java.util.HashMap;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

/**
 *
 * @author
 */
public class CustomLoginModule extends AppservPasswordLoginModule {

    private static HashMap<String, String> userlist = new HashMap<String, String>();
    private RadiusAuthentication auth = new RadiusMock();
    
    public CustomLoginModule() {
        System.out.println("CustomLoginModule LoginModule - Construction");
            //mock -> stativ user
        userlist.put("user1", "user");
        userlist.put("user2", "user");
        userlist.put("user3", "user");
        userlist.put("admin", "administrator");
    }

    @Override
    protected void authenticateUser() throws LoginException {
        System.out.println("Going to Log In ............................");
        String userString = _username;
        String password = new String(_passwd);
        String pwd = userlist.get(_username);
        System.out.println(userString + "/" + password);

        if (pwd != null && auth.authenticate(_username, password)) {
            String[] groups = new String[1]; //{"user"};
            groups[0] = userlist.get(_username);
            commitUserAuthentication(groups);
            
        } else {
           throw new LoginException("Password missmatch"); 
        }
            

    }

}

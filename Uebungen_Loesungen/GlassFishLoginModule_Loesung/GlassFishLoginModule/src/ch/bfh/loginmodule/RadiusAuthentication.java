
package ch.bfh.loginmodule;

/**
 * Simple Radius LoginModule
 * @author AP
 */
public interface RadiusAuthentication {
  
    public boolean authenticate (String username, String password);
    
}


package ch.bfh.ejb;

import javax.ejb.Stateless;

/**
 *
 * AP 
 */
@Stateless
public class SecureServiceEJB implements SecureServiceEJBLocal {
    
    @Override
    public String echo(String input) {
      return input;
    }

    @Override
    public String readInternalData() {
        String name = "???"; 

        return "readInternalData for role " + name ;
    }

    @Override
    public String readConfidentalData() {
        String name = "???";
        return "readConfidentalData for " + name ;
    }
    
}


package ch.bfh.ejb;

import javax.ejb.Local;

/**
 *
 * @author 
 */
@Local
public interface SecureServiceEJBLocal {
 
    public String echo(String input);
 
    public String readInternalData(); 
    
    public String readConfidentalData();
}

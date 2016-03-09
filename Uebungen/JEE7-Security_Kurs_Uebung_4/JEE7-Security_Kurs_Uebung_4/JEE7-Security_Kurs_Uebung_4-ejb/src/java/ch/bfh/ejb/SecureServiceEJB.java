
package ch.bfh.ejb;

import java.security.Principal;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;

/**
 *
 * AP 
 */
@Stateless
@DeclareRoles({"user", "administrator"})
public class SecureServiceEJB implements SecureServiceEJBLocal {
    private static final Logger LOG = Logger.getLogger(SecureServiceEJB.class.getName());
    @Resource EJBContext ctx;
    
    @Override   
    @PermitAll
    public String echo(String input) {
      Principal princip = ctx.getCallerPrincipal();
      LOG.info("echo " + princip.getName());
      return input;
    }

    @Override
    @RolesAllowed({"user", "administrator"})
    public String readInternalData() {
        String name = ctx.getCallerPrincipal().getName(); 
        Principal princip = ctx.getCallerPrincipal();
        LOG.info("readInternaldata " + princip.getName());
      
        return "readInternalData for role " + name ;
    }

    @Override
    @RolesAllowed({"administrator"})
    public String readConfidentalData() {
        LOG.info("readInternaldata " + ctx.getCallerPrincipal().getName());
        return "readConfidentalData for " + ctx.getCallerPrincipal().getName() ;
    }
    
}

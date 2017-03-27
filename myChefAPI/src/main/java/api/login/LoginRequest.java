/*
 * 
 * 
 */
package api.login;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al
 */
@XmlRootElement
public class LoginRequest {
   @XmlElement 
   public String login;
   @XmlElement 
   public String password;
}

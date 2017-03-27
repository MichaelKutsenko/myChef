/*
 * 
 * 
 */
package api.login;

import api.user.JSONuser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author al
 */
    @XmlRootElement
public class LoginReply {
    @XmlElement
    public String token="";
    @XmlElement
    public JSONuser user;
}

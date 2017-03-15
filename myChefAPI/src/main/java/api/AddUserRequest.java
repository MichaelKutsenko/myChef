
package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Created by Mocart
 */
@XmlRootElement
public class AddUserRequest {
    @XmlElement(required=true)
    public JSONuser user;
}

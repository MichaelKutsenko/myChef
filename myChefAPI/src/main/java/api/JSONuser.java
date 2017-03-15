package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mocart
 */

@XmlRootElement
public class JSONuser {
    @XmlElement(required=false)
    public Long id;
    @XmlElement(required=true)
    public String login;
    @XmlElement(required=true)
    public String email;

    @XmlElement(required=true)
    public String firstName;
    @XmlElement(required=true)
    public String lastName;
    @XmlElement(required=false)
    public String phone;
    @XmlElement(required=true)
    public double grade;
    @XmlElement(required=true)
    public int gradeCouner;
    @XmlElement(required=true)
    public long city_id;
    @XmlElement(required=true)
    public Boolean isChef;

    @XmlElement(required=false)
    public JSONchef chef;
}

package api.event;

import api.geo.JSONgeoPoint;
import api.user.JSONuser;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Mocart
 */

@XmlRootElement
public class JSONevent {
    @XmlElement(required=false)
    public Long id;
    @XmlElement(required=true)
    public String userFirstName;
    @XmlElement(required=true)
    public long userID;
    @XmlElement(required=false)
    public int year;
    @XmlElement(required=false)
    public int month;
    @XmlElement(required=false)
    public int day;
    @XmlElement(required=true)
    public String description;

    @XmlElement(required=true)
    public JSONgeoPoint city;

}

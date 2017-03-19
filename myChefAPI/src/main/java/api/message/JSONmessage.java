package api.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mocart
 */
@XmlRootElement
public class JSONmessage {
    @XmlElement(required=false)
    public Long id;
    @XmlElement(required=true)
    public long eventID;
    @XmlElement(required=true)
    public String userFirstName;
    @XmlElement(required=true)
    public long userID;
    @XmlElement(required=false)
    public String date;
    @XmlElement(required=true)
    public String description;
}

package api.feedback;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mocart
 */
@XmlRootElement
public class JSONfeedback {
    @XmlElement(required=false)
    public Long id;
    @XmlElement(required=true)
    public long fromUserID;
    @XmlElement(required=true)
    public String fromUserFirstName;
    @XmlElement(required=true)
    public long toUserID;
    @XmlElement(required=true)
    public String date;
    @XmlElement(required=true)
    public String description;
    @XmlElement(required=true)
    public int grade;
}

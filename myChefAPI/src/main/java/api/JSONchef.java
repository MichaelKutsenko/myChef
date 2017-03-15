package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mocart
 */

@XmlRootElement
public class JSONchef {
    @XmlElement(required=true)
    public int pricePerHour;
    @XmlElement(required=true)
    public int minPrice;
    @XmlElement(required=true)
    public String description;
}

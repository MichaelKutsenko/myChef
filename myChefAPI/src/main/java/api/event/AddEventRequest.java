package api.event;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Mocart
 */
public class AddEventRequest {
    @XmlElement(required=true)
    public JSONevent event;
}

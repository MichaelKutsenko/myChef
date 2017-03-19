package api.message;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Mocart
 */
public class AddMessageRequest {
    @XmlElement(required=true)
    public JSONmessage message;
}

package api.feedback;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Mocart
 */
public class AddFeedbackReply {
    @XmlElement(required=true)
    public JSONfeedback feedback;
}

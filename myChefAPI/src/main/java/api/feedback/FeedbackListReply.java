package api.feedback;

import api.GenericReply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart
 */
@XmlRootElement
public class FeedbackListReply extends GenericReply{
    @XmlElement(required=true)
    public List<JSONfeedback> feedbacks = new ArrayList<>();
}

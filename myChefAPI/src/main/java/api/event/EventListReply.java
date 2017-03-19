package api.event;

import api.GenericReply;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart
 */
public class EventListReply extends GenericReply {
    @XmlElement(required=true)
    public List<JSONevent> events = new ArrayList<>();
}

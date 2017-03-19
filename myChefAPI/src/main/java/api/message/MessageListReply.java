package api.message;

import api.GenericReply;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart
 */
public class MessageListReply extends GenericReply {
    @XmlElement(required=true)
    public List<JSONmessage> messages = new ArrayList<>();
}

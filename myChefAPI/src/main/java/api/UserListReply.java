package api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart
 */
@XmlRootElement
public class UserListReply extends GenericReply{
    @XmlElement(required=true)
    public List<JSONuser> users = new ArrayList<>();
}

package api.geo;

import api.GenericReply;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart
 */
@XmlRootElement
public class GEOListReply  extends GenericReply {
    @XmlElement(required=true)
    public List<JSONgeoPoint> geoPoints = new ArrayList<>();
}

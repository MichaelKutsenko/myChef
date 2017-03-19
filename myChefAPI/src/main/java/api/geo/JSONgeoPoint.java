package api.geo;

import api.user.JSONchef;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Mocart
 */
@XmlRootElement
public class JSONgeoPoint {
        @XmlElement(required=true)
        public Long id;
        @XmlElement(required=true)
        public String name;
}

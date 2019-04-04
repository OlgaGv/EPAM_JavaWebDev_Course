//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.02 at 02:51:45 PM MSK 
//

package by.training.task4.entity;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Origin.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Origin">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AFRICA"/>
 *     &lt;enumeration value="ASIA"/>
 *     &lt;enumeration value="ANTARCTICA"/>
 *     &lt;enumeration value="SOUTH AMERICA"/>
 *     &lt;enumeration value="NORTH AMERICA"/>
 *     &lt;enumeration value="EUROPE"/>
 *     &lt;enumeration value="AUSTRALIA"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Origin")
@XmlEnum
public enum Origin {

    AFRICA("AFRICA"),
    ASIA("ASIA"),
    ANTARCTICA("ANTARCTICA"),
    @XmlEnumValue("SOUTH AMERICA")
    SOUTH_AMERICA("SOUTH AMERICA"),
    @XmlEnumValue("NORTH AMERICA")
    NORTH_AMERICA("NORTH AMERICA"),
    EUROPE("EUROPE"),
    AUSTRALIA("AUSTRALIA"),
    OTHER("OTHER");
    private final String value;

    Origin(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Origin fromValue(String v) {
        for (Origin c: Origin.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

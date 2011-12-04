
package client.artefact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for relation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="relation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_client_from" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_client_to" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "relation", propOrder = {
    "id",
    "idClientFrom",
    "idClientTo",
    "status"
})
public class Relation {

    protected int id;
    @XmlElement(name = "id_client_from")
    protected int idClientFrom;
    @XmlElement(name = "id_client_to")
    protected int idClientTo;
    @XmlSchemaType(name = "unsignedShort")
    protected int status;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the idClientFrom property.
     * 
     */
    public int getIdClientFrom() {
        return idClientFrom;
    }

    /**
     * Sets the value of the idClientFrom property.
     * 
     */
    public void setIdClientFrom(int value) {
        this.idClientFrom = value;
    }

    /**
     * Gets the value of the idClientTo property.
     * 
     */
    public int getIdClientTo() {
        return idClientTo;
    }

    /**
     * Sets the value of the idClientTo property.
     * 
     */
    public void setIdClientTo(int value) {
        this.idClientTo = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

}


package client.artefact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for message complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="message">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_client_from" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_client_to" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_photo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="is_private" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="is_read" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="msg_date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "message", propOrder = {
    "id",
    "idClientFrom",
    "idClientTo",
    "idPhoto",
    "isPrivate",
    "isRead",
    "msgDate",
    "text"
})
public class Message {

    protected int id;
    @XmlElement(name = "id_client_from")
    protected int idClientFrom;
    @XmlElement(name = "id_client_to")
    protected int idClientTo;
    @XmlElement(name = "id_photo")
    protected int idPhoto;
    @XmlElement(name = "is_private")
    protected Boolean isPrivate;
    @XmlElement(name = "is_read")
    protected Boolean isRead;
    @XmlElement(name = "msg_date")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar msgDate;
    protected String text;

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
     * Gets the value of the idPhoto property.
     * 
     */
    public int getIdPhoto() {
        return idPhoto;
    }

    /**
     * Sets the value of the idPhoto property.
     * 
     */
    public void setIdPhoto(int value) {
        this.idPhoto = value;
    }

    /**
     * Gets the value of the isPrivate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPrivate() {
        return isPrivate;
    }

    /**
     * Sets the value of the isPrivate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPrivate(Boolean value) {
        this.isPrivate = value;
    }

    /**
     * Gets the value of the isRead property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsRead() {
        return isRead;
    }

    /**
     * Sets the value of the isRead property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsRead(Boolean value) {
        this.isRead = value;
    }

    /**
     * Gets the value of the msgDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMsgDate() {
        return msgDate;
    }

    /**
     * Sets the value of the msgDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMsgDate(XMLGregorianCalendar value) {
        this.msgDate = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.11.24 at 02:18:30 PM CET 
//


package com.gladigator.Entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gladigator.Entities package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CalculateBMIRequest_QNAME = new QName("http://gladigator.com/", "calculateBMIRequest");
    private final static QName _CalculateBMIResponse_QNAME = new QName("http://gladigator.com/", "calculateBMIResponse");
    private final static QName _CalculateBMRRequest_QNAME = new QName("http://gladigator.com/", "calculateBMRRequest");
    private final static QName _CalculateBMRResponse_QNAME = new QName("http://gladigator.com/", "calculateBMRResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gladigator.Entities
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateBMIRequest }
     * 
     */
    public CalculateBMIRequest createCalculateBMIRequest() {
        return new CalculateBMIRequest();
    }

    /**
     * Create an instance of {@link CalculateBMIResponse }
     * 
     */
    public CalculateBMIResponse createCalculateBMIResponse() {
        return new CalculateBMIResponse();
    }

    /**
     * Create an instance of {@link CalculateBMRRequest }
     * 
     */
    public CalculateBMRRequest createCalculateBMRRequest() {
        return new CalculateBMRRequest();
    }

    /**
     * Create an instance of {@link CalculateBMRResponse }
     * 
     */
    public CalculateBMRResponse createCalculateBMRResponse() {
        return new CalculateBMRResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBMIRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CalculateBMIRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://gladigator.com/", name = "calculateBMIRequest")
    public JAXBElement<CalculateBMIRequest> createCalculateBMIRequest(CalculateBMIRequest value) {
        return new JAXBElement<CalculateBMIRequest>(_CalculateBMIRequest_QNAME, CalculateBMIRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBMIResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CalculateBMIResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://gladigator.com/", name = "calculateBMIResponse")
    public JAXBElement<CalculateBMIResponse> createCalculateBMIResponse(CalculateBMIResponse value) {
        return new JAXBElement<CalculateBMIResponse>(_CalculateBMIResponse_QNAME, CalculateBMIResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBMRRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CalculateBMRRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://gladigator.com/", name = "calculateBMRRequest")
    public JAXBElement<CalculateBMRRequest> createCalculateBMRRequest(CalculateBMRRequest value) {
        return new JAXBElement<CalculateBMRRequest>(_CalculateBMRRequest_QNAME, CalculateBMRRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateBMRResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CalculateBMRResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://gladigator.com/", name = "calculateBMRResponse")
    public JAXBElement<CalculateBMRResponse> createCalculateBMRResponse(CalculateBMRResponse value) {
        return new JAXBElement<CalculateBMRResponse>(_CalculateBMRResponse_QNAME, CalculateBMRResponse.class, null, value);
    }

}

package com.gladigator.Services;

import java.util.Objects;

import javax.xml.bind.JAXBElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.gladigator.Entities.CalculateBMIRequest;
import com.gladigator.Entities.CalculateBMIResponse;
import com.gladigator.Entities.CalculateBMRRequest;
import com.gladigator.Entities.CalculateBMRResponse;
import com.gladigator.Entities.ObjectFactory;


public class BmiBmrServiceClient extends WebServiceGatewaySupport {

    private static final Logger LOG = LogManager.getLogger(BmiBmrServiceClient.class);
    
    @Autowired
    private ObjectFactory bmiBmrObjectFactory;
    
    @SuppressWarnings("unchecked")
    public CalculateBMIResponse callBmiService(CalculateBMIRequest request) {
	JAXBElement<CalculateBMIRequest> wrappedRequest = bmiBmrObjectFactory.createCalculateBMIRequest(request);
	JAXBElement<CalculateBMIResponse> wrappedResponse = (JAXBElement<CalculateBMIResponse>) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8090/bmibmrcalculatorws/bmibmrcalculator", wrappedRequest);
	CalculateBMIResponse response = wrappedResponse.getValue();
	LOG.info("Response calculated BMI = {}", Objects.isNull(response.getCalculatedBMI()));
	return response;
    }
    
    @SuppressWarnings("unchecked")
    public CalculateBMRResponse callBmrService(CalculateBMRRequest request) {
	JAXBElement<CalculateBMRRequest> wrappedRequest = bmiBmrObjectFactory.createCalculateBMRRequest(request);
	JAXBElement<CalculateBMRResponse> wrappedResponse = (JAXBElement<CalculateBMRResponse>) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8090/bmibmrcalculatorws/bmibmrcalculator", wrappedRequest);
	CalculateBMRResponse response = wrappedResponse.getValue();
	LOG.info("Response calculated BMI = {}", Objects.isNull(response.getCalculatedBMRResponse()));
	return response;
    }
}

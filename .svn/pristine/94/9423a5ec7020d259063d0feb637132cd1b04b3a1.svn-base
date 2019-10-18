/**
 * AzkjLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AzkjLocator extends org.apache.axis.client.Service implements Azkj {

/**
 * gSOAP 2.8.23 generated service definition
 */

    public AzkjLocator() {
    }


    public AzkjLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AzkjLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for azkj
    @Value("${netease.url}")
    private String azkj_address;

    public String getazkjAddress() {
        return azkj_address;
    }

    public String getAzkj_address() {
        return azkj_address;
    }

    public void setAzkj_address(String azkj_address) {
        this.azkj_address = azkj_address;
    }

    // The WSDD service name defaults to the port name.
    private String azkjWSDDServiceName = "azkj";

    public String getazkjWSDDServiceName() {
        return azkjWSDDServiceName;
    }

    public void setazkjWSDDServiceName(String name) {
        azkjWSDDServiceName = name;
    }

    public AzkjPortType getazkj() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(azkj_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getazkj(endpoint);
    }

    public AzkjPortType getazkj(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            AzkjStub _stub = new AzkjStub(portAddress, this);
            _stub.setPortName(getazkjWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setazkjEndpointAddress(String address) {
        azkj_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (AzkjPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                AzkjStub _stub = new AzkjStub(new java.net.URL(azkj_address), this);
                _stub.setPortName(getazkjWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("azkj".equals(inputPortName)) {
            return getazkj();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName(getAzkj_address()+"/azkj.wsdl", "azkj");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName(getAzkj_address()+"/azkj.wsdl", "azkj"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("azkj".equals(portName)) {
            setazkjEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

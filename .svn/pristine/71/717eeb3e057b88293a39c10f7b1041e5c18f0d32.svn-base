/**
 * RouteWithRelay.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class RouteWithRelay  implements java.io.Serializable {
    private int routeID;

    private int relayID;

    public RouteWithRelay() {
    }

    public RouteWithRelay(
           int routeID,
           int relayID) {
           this.routeID = routeID;
           this.relayID = relayID;
    }


    /**
     * Gets the routeID value for this RouteWithRelay.
     * 
     * @return routeID
     */
    public int getRouteID() {
        return routeID;
    }


    /**
     * Sets the routeID value for this RouteWithRelay.
     * 
     * @param routeID
     */
    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }


    /**
     * Gets the relayID value for this RouteWithRelay.
     * 
     * @return relayID
     */
    public int getRelayID() {
        return relayID;
    }


    /**
     * Sets the relayID value for this RouteWithRelay.
     * 
     * @param relayID
     */
    public void setRelayID(int relayID) {
        this.relayID = relayID;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RouteWithRelay)) return false;
        RouteWithRelay other = (RouteWithRelay) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.routeID == other.getRouteID() &&
            this.relayID == other.getRelayID();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getRouteID();
        _hashCode += getRelayID();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RouteWithRelay.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "RouteWithRelay"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RouteID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relayID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelayID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

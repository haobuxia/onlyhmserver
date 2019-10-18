/**
 * SysParameter.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class SysParameter  implements java.io.Serializable {
    private int ID;

    private String theKey;

    private String theValue;

    private int theState;

    public SysParameter() {
    }

    public SysParameter(
           int ID,
           String theKey,
           String theValue,
           int theState) {
           this.ID = ID;
           this.theKey = theKey;
           this.theValue = theValue;
           this.theState = theState;
    }


    /**
     * Gets the ID value for this SysParameter.
     * 
     * @return ID
     */
    public int getID() {
        return ID;
    }


    /**
     * Sets the ID value for this SysParameter.
     * 
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Gets the theKey value for this SysParameter.
     * 
     * @return theKey
     */
    public String getTheKey() {
        return theKey;
    }


    /**
     * Sets the theKey value for this SysParameter.
     * 
     * @param theKey
     */
    public void setTheKey(String theKey) {
        this.theKey = theKey;
    }


    /**
     * Gets the theValue value for this SysParameter.
     * 
     * @return theValue
     */
    public String getTheValue() {
        return theValue;
    }


    /**
     * Sets the theValue value for this SysParameter.
     * 
     * @param theValue
     */
    public void setTheValue(String theValue) {
        this.theValue = theValue;
    }


    /**
     * Gets the theState value for this SysParameter.
     * 
     * @return theState
     */
    public int getTheState() {
        return theState;
    }


    /**
     * Sets the theState value for this SysParameter.
     * 
     * @param theState
     */
    public void setTheState(int theState) {
        this.theState = theState;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SysParameter)) return false;
        SysParameter other = (SysParameter) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ID == other.getID() &&
            ((this.theKey==null && other.getTheKey()==null) || 
             (this.theKey!=null &&
              this.theKey.equals(other.getTheKey()))) &&
            ((this.theValue==null && other.getTheValue()==null) || 
             (this.theValue!=null &&
              this.theValue.equals(other.getTheValue()))) &&
            this.theState == other.getTheState();
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
        _hashCode += getID();
        if (getTheKey() != null) {
            _hashCode += getTheKey().hashCode();
        }
        if (getTheValue() != null) {
            _hashCode += getTheValue().hashCode();
        }
        _hashCode += getTheState();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SysParameter.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "SysParameter"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theKey");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TheKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theValue");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TheValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TheState"));
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

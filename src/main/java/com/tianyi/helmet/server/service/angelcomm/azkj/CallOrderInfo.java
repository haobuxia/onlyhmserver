/**
 * CallOrderInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class CallOrderInfo  implements java.io.Serializable {
    private int id;

    private String called;

    private String prefixcalled;

    private int thestate;

    private CallOrderDetail[] details;

    public CallOrderInfo() {
    }

    public CallOrderInfo(
           int id,
           String called,
           String prefixcalled,
           int thestate,
           CallOrderDetail[] details) {
           this.id = id;
           this.called = called;
           this.prefixcalled = prefixcalled;
           this.thestate = thestate;
           this.details = details;
    }


    /**
     * Gets the id value for this CallOrderInfo.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this CallOrderInfo.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the called value for this CallOrderInfo.
     * 
     * @return called
     */
    public String getCalled() {
        return called;
    }


    /**
     * Sets the called value for this CallOrderInfo.
     * 
     * @param called
     */
    public void setCalled(String called) {
        this.called = called;
    }


    /**
     * Gets the prefixcalled value for this CallOrderInfo.
     * 
     * @return prefixcalled
     */
    public String getPrefixcalled() {
        return prefixcalled;
    }


    /**
     * Sets the prefixcalled value for this CallOrderInfo.
     * 
     * @param prefixcalled
     */
    public void setPrefixcalled(String prefixcalled) {
        this.prefixcalled = prefixcalled;
    }


    /**
     * Gets the thestate value for this CallOrderInfo.
     * 
     * @return thestate
     */
    public int getThestate() {
        return thestate;
    }


    /**
     * Sets the thestate value for this CallOrderInfo.
     * 
     * @param thestate
     */
    public void setThestate(int thestate) {
        this.thestate = thestate;
    }


    /**
     * Gets the details value for this CallOrderInfo.
     * 
     * @return details
     */
    public CallOrderDetail[] getDetails() {
        return details;
    }


    /**
     * Sets the details value for this CallOrderInfo.
     * 
     * @param details
     */
    public void setDetails(CallOrderDetail[] details) {
        this.details = details;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CallOrderInfo)) return false;
        CallOrderInfo other = (CallOrderInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.called==null && other.getCalled()==null) || 
             (this.called!=null &&
              this.called.equals(other.getCalled()))) &&
            ((this.prefixcalled==null && other.getPrefixcalled()==null) || 
             (this.prefixcalled!=null &&
              this.prefixcalled.equals(other.getPrefixcalled()))) &&
            this.thestate == other.getThestate() &&
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              java.util.Arrays.equals(this.details, other.getDetails())));
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
        _hashCode += getId();
        if (getCalled() != null) {
            _hashCode += getCalled().hashCode();
        }
        if (getPrefixcalled() != null) {
            _hashCode += getPrefixcalled().hashCode();
        }
        _hashCode += getThestate();
        if (getDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDetails());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CallOrderInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "CallOrderInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("called");
        elemField.setXmlName(new javax.xml.namespace.QName("", "called"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefixcalled");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prefixcalled"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("thestate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "thestate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:azkj", "CallOrderDetail"));
        elemField.setNillable(true);
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

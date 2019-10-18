/**
 * CallOrderDetail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class CallOrderDetail  implements java.io.Serializable {
    private int parentID;

    private String number;

    private String prefixnum;

    private int theindex;

    private int ringtime;

    public CallOrderDetail() {
    }

    public CallOrderDetail(
           int parentID,
           String number,
           String prefixnum,
           int theindex,
           int ringtime) {
           this.parentID = parentID;
           this.number = number;
           this.prefixnum = prefixnum;
           this.theindex = theindex;
           this.ringtime = ringtime;
    }


    /**
     * Gets the parentID value for this CallOrderDetail.
     * 
     * @return parentID
     */
    public int getParentID() {
        return parentID;
    }


    /**
     * Sets the parentID value for this CallOrderDetail.
     * 
     * @param parentID
     */
    public void setParentID(int parentID) {
        this.parentID = parentID;
    }


    /**
     * Gets the number value for this CallOrderDetail.
     * 
     * @return number
     */
    public String getNumber() {
        return number;
    }


    /**
     * Sets the number value for this CallOrderDetail.
     * 
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }


    /**
     * Gets the prefixnum value for this CallOrderDetail.
     * 
     * @return prefixnum
     */
    public String getPrefixnum() {
        return prefixnum;
    }


    /**
     * Sets the prefixnum value for this CallOrderDetail.
     * 
     * @param prefixnum
     */
    public void setPrefixnum(String prefixnum) {
        this.prefixnum = prefixnum;
    }


    /**
     * Gets the theindex value for this CallOrderDetail.
     * 
     * @return theindex
     */
    public int getTheindex() {
        return theindex;
    }


    /**
     * Sets the theindex value for this CallOrderDetail.
     * 
     * @param theindex
     */
    public void setTheindex(int theindex) {
        this.theindex = theindex;
    }


    /**
     * Gets the ringtime value for this CallOrderDetail.
     * 
     * @return ringtime
     */
    public int getRingtime() {
        return ringtime;
    }


    /**
     * Sets the ringtime value for this CallOrderDetail.
     * 
     * @param ringtime
     */
    public void setRingtime(int ringtime) {
        this.ringtime = ringtime;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CallOrderDetail)) return false;
        CallOrderDetail other = (CallOrderDetail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.parentID == other.getParentID() &&
            ((this.number==null && other.getNumber()==null) || 
             (this.number!=null &&
              this.number.equals(other.getNumber()))) &&
            ((this.prefixnum==null && other.getPrefixnum()==null) || 
             (this.prefixnum!=null &&
              this.prefixnum.equals(other.getPrefixnum()))) &&
            this.theindex == other.getTheindex() &&
            this.ringtime == other.getRingtime();
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
        _hashCode += getParentID();
        if (getNumber() != null) {
            _hashCode += getNumber().hashCode();
        }
        if (getPrefixnum() != null) {
            _hashCode += getPrefixnum().hashCode();
        }
        _hashCode += getTheindex();
        _hashCode += getRingtime();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CallOrderDetail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "CallOrderDetail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ParentID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("", "number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prefixnum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "prefixnum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theindex");
        elemField.setXmlName(new javax.xml.namespace.QName("", "theindex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ringtime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ringtime"));
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

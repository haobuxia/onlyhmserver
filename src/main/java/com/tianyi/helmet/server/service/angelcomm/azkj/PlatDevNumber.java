/**
 * PlatDevNumber.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class PlatDevNumber  implements java.io.Serializable {
    private String dev;

    private String num;

    private String name;

    private String fatherid;

    private double longitude;

    private double latitude;

    private String domain;

    private int ptztype;

    private int status;

    private String modifytime;

    public PlatDevNumber() {
    }

    public PlatDevNumber(
           String dev,
           String num,
           String name,
           String fatherid,
           double longitude,
           double latitude,
           String domain,
           int ptztype,
           int status,
           String modifytime) {
           this.dev = dev;
           this.num = num;
           this.name = name;
           this.fatherid = fatherid;
           this.longitude = longitude;
           this.latitude = latitude;
           this.domain = domain;
           this.ptztype = ptztype;
           this.status = status;
           this.modifytime = modifytime;
    }


    /**
     * Gets the dev value for this PlatDevNumber.
     * 
     * @return dev
     */
    public String getDev() {
        return dev;
    }


    /**
     * Sets the dev value for this PlatDevNumber.
     * 
     * @param dev
     */
    public void setDev(String dev) {
        this.dev = dev;
    }


    /**
     * Gets the num value for this PlatDevNumber.
     * 
     * @return num
     */
    public String getNum() {
        return num;
    }


    /**
     * Sets the num value for this PlatDevNumber.
     * 
     * @param num
     */
    public void setNum(String num) {
        this.num = num;
    }


    /**
     * Gets the name value for this PlatDevNumber.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this PlatDevNumber.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the fatherid value for this PlatDevNumber.
     * 
     * @return fatherid
     */
    public String getFatherid() {
        return fatherid;
    }


    /**
     * Sets the fatherid value for this PlatDevNumber.
     * 
     * @param fatherid
     */
    public void setFatherid(String fatherid) {
        this.fatherid = fatherid;
    }


    /**
     * Gets the longitude value for this PlatDevNumber.
     * 
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }


    /**
     * Sets the longitude value for this PlatDevNumber.
     * 
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    /**
     * Gets the latitude value for this PlatDevNumber.
     * 
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Sets the latitude value for this PlatDevNumber.
     * 
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    /**
     * Gets the domain value for this PlatDevNumber.
     * 
     * @return domain
     */
    public String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this PlatDevNumber.
     * 
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }


    /**
     * Gets the ptztype value for this PlatDevNumber.
     * 
     * @return ptztype
     */
    public int getPtztype() {
        return ptztype;
    }


    /**
     * Sets the ptztype value for this PlatDevNumber.
     * 
     * @param ptztype
     */
    public void setPtztype(int ptztype) {
        this.ptztype = ptztype;
    }


    /**
     * Gets the status value for this PlatDevNumber.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this PlatDevNumber.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the modifytime value for this PlatDevNumber.
     * 
     * @return modifytime
     */
    public String getModifytime() {
        return modifytime;
    }


    /**
     * Sets the modifytime value for this PlatDevNumber.
     * 
     * @param modifytime
     */
    public void setModifytime(String modifytime) {
        this.modifytime = modifytime;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlatDevNumber)) return false;
        PlatDevNumber other = (PlatDevNumber) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dev==null && other.getDev()==null) || 
             (this.dev!=null &&
              this.dev.equals(other.getDev()))) &&
            ((this.num==null && other.getNum()==null) || 
             (this.num!=null &&
              this.num.equals(other.getNum()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.fatherid==null && other.getFatherid()==null) || 
             (this.fatherid!=null &&
              this.fatherid.equals(other.getFatherid()))) &&
            this.longitude == other.getLongitude() &&
            this.latitude == other.getLatitude() &&
            ((this.domain==null && other.getDomain()==null) || 
             (this.domain!=null &&
              this.domain.equals(other.getDomain()))) &&
            this.ptztype == other.getPtztype() &&
            this.status == other.getStatus() &&
            ((this.modifytime==null && other.getModifytime()==null) || 
             (this.modifytime!=null &&
              this.modifytime.equals(other.getModifytime())));
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
        if (getDev() != null) {
            _hashCode += getDev().hashCode();
        }
        if (getNum() != null) {
            _hashCode += getNum().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getFatherid() != null) {
            _hashCode += getFatherid().hashCode();
        }
        _hashCode += new Double(getLongitude()).hashCode();
        _hashCode += new Double(getLatitude()).hashCode();
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        _hashCode += getPtztype();
        _hashCode += getStatus();
        if (getModifytime() != null) {
            _hashCode += getModifytime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlatDevNumber.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "PlatDevNumber"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dev");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dev"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("num");
        elemField.setXmlName(new javax.xml.namespace.QName("", "num"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatherid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fatherid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longitude");
        elemField.setXmlName(new javax.xml.namespace.QName("", "longitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latitude");
        elemField.setXmlName(new javax.xml.namespace.QName("", "latitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("", "domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ptztype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ptztype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifytime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modifytime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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

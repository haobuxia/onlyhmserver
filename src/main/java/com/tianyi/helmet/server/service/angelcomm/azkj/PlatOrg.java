/**
 * PlatOrg.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class PlatOrg  implements java.io.Serializable {
    private String id;

    private String name;

    private String superid;

    private String ldap;

    private String domain;

    private int ordernum;

    public PlatOrg() {
    }

    public PlatOrg(
           String id,
           String name,
           String superid,
           String ldap,
           String domain,
           int ordernum) {
           this.id = id;
           this.name = name;
           this.superid = superid;
           this.ldap = ldap;
           this.domain = domain;
           this.ordernum = ordernum;
    }


    /**
     * Gets the id value for this PlatOrg.
     * 
     * @return id
     */
    public String getId() {
        return id;
    }


    /**
     * Sets the id value for this PlatOrg.
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }


    /**
     * Gets the name value for this PlatOrg.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name value for this PlatOrg.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Gets the superid value for this PlatOrg.
     * 
     * @return superid
     */
    public String getSuperid() {
        return superid;
    }


    /**
     * Sets the superid value for this PlatOrg.
     * 
     * @param superid
     */
    public void setSuperid(String superid) {
        this.superid = superid;
    }


    /**
     * Gets the ldap value for this PlatOrg.
     * 
     * @return ldap
     */
    public String getLdap() {
        return ldap;
    }


    /**
     * Sets the ldap value for this PlatOrg.
     * 
     * @param ldap
     */
    public void setLdap(String ldap) {
        this.ldap = ldap;
    }


    /**
     * Gets the domain value for this PlatOrg.
     * 
     * @return domain
     */
    public String getDomain() {
        return domain;
    }


    /**
     * Sets the domain value for this PlatOrg.
     * 
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }


    /**
     * Gets the ordernum value for this PlatOrg.
     * 
     * @return ordernum
     */
    public int getOrdernum() {
        return ordernum;
    }


    /**
     * Sets the ordernum value for this PlatOrg.
     * 
     * @param ordernum
     */
    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof PlatOrg)) return false;
        PlatOrg other = (PlatOrg) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.superid==null && other.getSuperid()==null) || 
             (this.superid!=null &&
              this.superid.equals(other.getSuperid()))) &&
            ((this.ldap==null && other.getLdap()==null) || 
             (this.ldap!=null &&
              this.ldap.equals(other.getLdap()))) &&
            ((this.domain==null && other.getDomain()==null) || 
             (this.domain!=null &&
              this.domain.equals(other.getDomain()))) &&
            this.ordernum == other.getOrdernum();
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getSuperid() != null) {
            _hashCode += getSuperid().hashCode();
        }
        if (getLdap() != null) {
            _hashCode += getLdap().hashCode();
        }
        if (getDomain() != null) {
            _hashCode += getDomain().hashCode();
        }
        _hashCode += getOrdernum();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PlatOrg.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "PlatOrg"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
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
        elemField.setFieldName("superid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "superid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ldap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ldap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("domain");
        elemField.setXmlName(new javax.xml.namespace.QName("", "domain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordernum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ordernum"));
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

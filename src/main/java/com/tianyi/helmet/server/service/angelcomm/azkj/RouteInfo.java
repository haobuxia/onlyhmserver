/**
 * RouteInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class RouteInfo  implements java.io.Serializable {
    private int id;

    private String routeNum;

    private String IP;

    private int callingPrefixNum;

    private String callingAddPrefix;

    private int calledPrefixNum;

    private String calledAddPrefix;

    private int routeType;

    private int inRouteMode;

    private String inDstNumber;

    private String routeDescribe;

    private String reserved;

    private String checkCallerNum;

    private String checkCalledNum;

    private String inSrcNumber;

    private String routeChangeAll;

    public RouteInfo() {
    }

    public RouteInfo(
           int id,
           String routeNum,
           String IP,
           int callingPrefixNum,
           String callingAddPrefix,
           int calledPrefixNum,
           String calledAddPrefix,
           int routeType,
           int inRouteMode,
           String inDstNumber,
           String routeDescribe,
           String reserved,
           String checkCallerNum,
           String checkCalledNum,
           String inSrcNumber,
           String routeChangeAll) {
           this.id = id;
           this.routeNum = routeNum;
           this.IP = IP;
           this.callingPrefixNum = callingPrefixNum;
           this.callingAddPrefix = callingAddPrefix;
           this.calledPrefixNum = calledPrefixNum;
           this.calledAddPrefix = calledAddPrefix;
           this.routeType = routeType;
           this.inRouteMode = inRouteMode;
           this.inDstNumber = inDstNumber;
           this.routeDescribe = routeDescribe;
           this.reserved = reserved;
           this.checkCallerNum = checkCallerNum;
           this.checkCalledNum = checkCalledNum;
           this.inSrcNumber = inSrcNumber;
           this.routeChangeAll = routeChangeAll;
    }


    /**
     * Gets the id value for this RouteInfo.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this RouteInfo.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the routeNum value for this RouteInfo.
     * 
     * @return routeNum
     */
    public String getRouteNum() {
        return routeNum;
    }


    /**
     * Sets the routeNum value for this RouteInfo.
     * 
     * @param routeNum
     */
    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }


    /**
     * Gets the IP value for this RouteInfo.
     * 
     * @return IP
     */
    public String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this RouteInfo.
     * 
     * @param IP
     */
    public void setIP(String IP) {
        this.IP = IP;
    }


    /**
     * Gets the callingPrefixNum value for this RouteInfo.
     * 
     * @return callingPrefixNum
     */
    public int getCallingPrefixNum() {
        return callingPrefixNum;
    }


    /**
     * Sets the callingPrefixNum value for this RouteInfo.
     * 
     * @param callingPrefixNum
     */
    public void setCallingPrefixNum(int callingPrefixNum) {
        this.callingPrefixNum = callingPrefixNum;
    }


    /**
     * Gets the callingAddPrefix value for this RouteInfo.
     * 
     * @return callingAddPrefix
     */
    public String getCallingAddPrefix() {
        return callingAddPrefix;
    }


    /**
     * Sets the callingAddPrefix value for this RouteInfo.
     * 
     * @param callingAddPrefix
     */
    public void setCallingAddPrefix(String callingAddPrefix) {
        this.callingAddPrefix = callingAddPrefix;
    }


    /**
     * Gets the calledPrefixNum value for this RouteInfo.
     * 
     * @return calledPrefixNum
     */
    public int getCalledPrefixNum() {
        return calledPrefixNum;
    }


    /**
     * Sets the calledPrefixNum value for this RouteInfo.
     * 
     * @param calledPrefixNum
     */
    public void setCalledPrefixNum(int calledPrefixNum) {
        this.calledPrefixNum = calledPrefixNum;
    }


    /**
     * Gets the calledAddPrefix value for this RouteInfo.
     * 
     * @return calledAddPrefix
     */
    public String getCalledAddPrefix() {
        return calledAddPrefix;
    }


    /**
     * Sets the calledAddPrefix value for this RouteInfo.
     * 
     * @param calledAddPrefix
     */
    public void setCalledAddPrefix(String calledAddPrefix) {
        this.calledAddPrefix = calledAddPrefix;
    }


    /**
     * Gets the routeType value for this RouteInfo.
     * 
     * @return routeType
     */
    public int getRouteType() {
        return routeType;
    }


    /**
     * Sets the routeType value for this RouteInfo.
     * 
     * @param routeType
     */
    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }


    /**
     * Gets the inRouteMode value for this RouteInfo.
     * 
     * @return inRouteMode
     */
    public int getInRouteMode() {
        return inRouteMode;
    }


    /**
     * Sets the inRouteMode value for this RouteInfo.
     * 
     * @param inRouteMode
     */
    public void setInRouteMode(int inRouteMode) {
        this.inRouteMode = inRouteMode;
    }


    /**
     * Gets the inDstNumber value for this RouteInfo.
     * 
     * @return inDstNumber
     */
    public String getInDstNumber() {
        return inDstNumber;
    }


    /**
     * Sets the inDstNumber value for this RouteInfo.
     * 
     * @param inDstNumber
     */
    public void setInDstNumber(String inDstNumber) {
        this.inDstNumber = inDstNumber;
    }


    /**
     * Gets the routeDescribe value for this RouteInfo.
     * 
     * @return routeDescribe
     */
    public String getRouteDescribe() {
        return routeDescribe;
    }


    /**
     * Sets the routeDescribe value for this RouteInfo.
     * 
     * @param routeDescribe
     */
    public void setRouteDescribe(String routeDescribe) {
        this.routeDescribe = routeDescribe;
    }


    /**
     * Gets the reserved value for this RouteInfo.
     * 
     * @return reserved
     */
    public String getReserved() {
        return reserved;
    }


    /**
     * Sets the reserved value for this RouteInfo.
     * 
     * @param reserved
     */
    public void setReserved(String reserved) {
        this.reserved = reserved;
    }


    /**
     * Gets the checkCallerNum value for this RouteInfo.
     * 
     * @return checkCallerNum
     */
    public String getCheckCallerNum() {
        return checkCallerNum;
    }


    /**
     * Sets the checkCallerNum value for this RouteInfo.
     * 
     * @param checkCallerNum
     */
    public void setCheckCallerNum(String checkCallerNum) {
        this.checkCallerNum = checkCallerNum;
    }


    /**
     * Gets the checkCalledNum value for this RouteInfo.
     * 
     * @return checkCalledNum
     */
    public String getCheckCalledNum() {
        return checkCalledNum;
    }


    /**
     * Sets the checkCalledNum value for this RouteInfo.
     * 
     * @param checkCalledNum
     */
    public void setCheckCalledNum(String checkCalledNum) {
        this.checkCalledNum = checkCalledNum;
    }


    /**
     * Gets the inSrcNumber value for this RouteInfo.
     * 
     * @return inSrcNumber
     */
    public String getInSrcNumber() {
        return inSrcNumber;
    }


    /**
     * Sets the inSrcNumber value for this RouteInfo.
     * 
     * @param inSrcNumber
     */
    public void setInSrcNumber(String inSrcNumber) {
        this.inSrcNumber = inSrcNumber;
    }


    /**
     * Gets the routeChangeAll value for this RouteInfo.
     * 
     * @return routeChangeAll
     */
    public String getRouteChangeAll() {
        return routeChangeAll;
    }


    /**
     * Sets the routeChangeAll value for this RouteInfo.
     * 
     * @param routeChangeAll
     */
    public void setRouteChangeAll(String routeChangeAll) {
        this.routeChangeAll = routeChangeAll;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof RouteInfo)) return false;
        RouteInfo other = (RouteInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.routeNum==null && other.getRouteNum()==null) || 
             (this.routeNum!=null &&
              this.routeNum.equals(other.getRouteNum()))) &&
            ((this.IP==null && other.getIP()==null) || 
             (this.IP!=null &&
              this.IP.equals(other.getIP()))) &&
            this.callingPrefixNum == other.getCallingPrefixNum() &&
            ((this.callingAddPrefix==null && other.getCallingAddPrefix()==null) || 
             (this.callingAddPrefix!=null &&
              this.callingAddPrefix.equals(other.getCallingAddPrefix()))) &&
            this.calledPrefixNum == other.getCalledPrefixNum() &&
            ((this.calledAddPrefix==null && other.getCalledAddPrefix()==null) || 
             (this.calledAddPrefix!=null &&
              this.calledAddPrefix.equals(other.getCalledAddPrefix()))) &&
            this.routeType == other.getRouteType() &&
            this.inRouteMode == other.getInRouteMode() &&
            ((this.inDstNumber==null && other.getInDstNumber()==null) || 
             (this.inDstNumber!=null &&
              this.inDstNumber.equals(other.getInDstNumber()))) &&
            ((this.routeDescribe==null && other.getRouteDescribe()==null) || 
             (this.routeDescribe!=null &&
              this.routeDescribe.equals(other.getRouteDescribe()))) &&
            ((this.reserved==null && other.getReserved()==null) || 
             (this.reserved!=null &&
              this.reserved.equals(other.getReserved()))) &&
            ((this.checkCallerNum==null && other.getCheckCallerNum()==null) || 
             (this.checkCallerNum!=null &&
              this.checkCallerNum.equals(other.getCheckCallerNum()))) &&
            ((this.checkCalledNum==null && other.getCheckCalledNum()==null) || 
             (this.checkCalledNum!=null &&
              this.checkCalledNum.equals(other.getCheckCalledNum()))) &&
            ((this.inSrcNumber==null && other.getInSrcNumber()==null) || 
             (this.inSrcNumber!=null &&
              this.inSrcNumber.equals(other.getInSrcNumber()))) &&
            ((this.routeChangeAll==null && other.getRouteChangeAll()==null) || 
             (this.routeChangeAll!=null &&
              this.routeChangeAll.equals(other.getRouteChangeAll())));
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
        if (getRouteNum() != null) {
            _hashCode += getRouteNum().hashCode();
        }
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        _hashCode += getCallingPrefixNum();
        if (getCallingAddPrefix() != null) {
            _hashCode += getCallingAddPrefix().hashCode();
        }
        _hashCode += getCalledPrefixNum();
        if (getCalledAddPrefix() != null) {
            _hashCode += getCalledAddPrefix().hashCode();
        }
        _hashCode += getRouteType();
        _hashCode += getInRouteMode();
        if (getInDstNumber() != null) {
            _hashCode += getInDstNumber().hashCode();
        }
        if (getRouteDescribe() != null) {
            _hashCode += getRouteDescribe().hashCode();
        }
        if (getReserved() != null) {
            _hashCode += getReserved().hashCode();
        }
        if (getCheckCallerNum() != null) {
            _hashCode += getCheckCallerNum().hashCode();
        }
        if (getCheckCalledNum() != null) {
            _hashCode += getCheckCalledNum().hashCode();
        }
        if (getInSrcNumber() != null) {
            _hashCode += getInSrcNumber().hashCode();
        }
        if (getRouteChangeAll() != null) {
            _hashCode += getRouteChangeAll().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RouteInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "RouteInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RouteNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callingPrefixNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CallingPrefixNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callingAddPrefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CallingAddPrefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calledPrefixNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CalledPrefixNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calledAddPrefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CalledAddPrefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RouteType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inRouteMode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InRouteMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inDstNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InDstNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeDescribe");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RouteDescribe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reserved");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Reserved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkCallerNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CheckCallerNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkCalledNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkCalledNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inSrcNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "InSrcNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("routeChangeAll");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RouteChangeAll"));
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

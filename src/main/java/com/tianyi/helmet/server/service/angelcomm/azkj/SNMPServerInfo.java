/**
 * SNMPServerInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class SNMPServerInfo  implements java.io.Serializable {
    private int ID;

    private String serverName;

    private String serverIP;

    private int serverPort;

    private int isUse;

    private int haveTrap;

    private int trapPort;

    private String remark;

    private int sysType;

    private String tableName;

    private String tableItemID;

    private int tableItemType;

    public SNMPServerInfo() {
    }

    public SNMPServerInfo(
           int ID,
           String serverName,
           String serverIP,
           int serverPort,
           int isUse,
           int haveTrap,
           int trapPort,
           String remark,
           int sysType,
           String tableName,
           String tableItemID,
           int tableItemType) {
           this.ID = ID;
           this.serverName = serverName;
           this.serverIP = serverIP;
           this.serverPort = serverPort;
           this.isUse = isUse;
           this.haveTrap = haveTrap;
           this.trapPort = trapPort;
           this.remark = remark;
           this.sysType = sysType;
           this.tableName = tableName;
           this.tableItemID = tableItemID;
           this.tableItemType = tableItemType;
    }


    /**
     * Gets the ID value for this SNMPServerInfo.
     * 
     * @return ID
     */
    public int getID() {
        return ID;
    }


    /**
     * Sets the ID value for this SNMPServerInfo.
     * 
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Gets the serverName value for this SNMPServerInfo.
     * 
     * @return serverName
     */
    public String getServerName() {
        return serverName;
    }


    /**
     * Sets the serverName value for this SNMPServerInfo.
     * 
     * @param serverName
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }


    /**
     * Gets the serverIP value for this SNMPServerInfo.
     * 
     * @return serverIP
     */
    public String getServerIP() {
        return serverIP;
    }


    /**
     * Sets the serverIP value for this SNMPServerInfo.
     * 
     * @param serverIP
     */
    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }


    /**
     * Gets the serverPort value for this SNMPServerInfo.
     * 
     * @return serverPort
     */
    public int getServerPort() {
        return serverPort;
    }


    /**
     * Sets the serverPort value for this SNMPServerInfo.
     * 
     * @param serverPort
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }


    /**
     * Gets the isUse value for this SNMPServerInfo.
     * 
     * @return isUse
     */
    public int getIsUse() {
        return isUse;
    }


    /**
     * Sets the isUse value for this SNMPServerInfo.
     * 
     * @param isUse
     */
    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }


    /**
     * Gets the haveTrap value for this SNMPServerInfo.
     * 
     * @return haveTrap
     */
    public int getHaveTrap() {
        return haveTrap;
    }


    /**
     * Sets the haveTrap value for this SNMPServerInfo.
     * 
     * @param haveTrap
     */
    public void setHaveTrap(int haveTrap) {
        this.haveTrap = haveTrap;
    }


    /**
     * Gets the trapPort value for this SNMPServerInfo.
     * 
     * @return trapPort
     */
    public int getTrapPort() {
        return trapPort;
    }


    /**
     * Sets the trapPort value for this SNMPServerInfo.
     * 
     * @param trapPort
     */
    public void setTrapPort(int trapPort) {
        this.trapPort = trapPort;
    }


    /**
     * Gets the remark value for this SNMPServerInfo.
     * 
     * @return remark
     */
    public String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this SNMPServerInfo.
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * Gets the sysType value for this SNMPServerInfo.
     * 
     * @return sysType
     */
    public int getSysType() {
        return sysType;
    }


    /**
     * Sets the sysType value for this SNMPServerInfo.
     * 
     * @param sysType
     */
    public void setSysType(int sysType) {
        this.sysType = sysType;
    }


    /**
     * Gets the tableName value for this SNMPServerInfo.
     * 
     * @return tableName
     */
    public String getTableName() {
        return tableName;
    }


    /**
     * Sets the tableName value for this SNMPServerInfo.
     * 
     * @param tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    /**
     * Gets the tableItemID value for this SNMPServerInfo.
     * 
     * @return tableItemID
     */
    public String getTableItemID() {
        return tableItemID;
    }


    /**
     * Sets the tableItemID value for this SNMPServerInfo.
     * 
     * @param tableItemID
     */
    public void setTableItemID(String tableItemID) {
        this.tableItemID = tableItemID;
    }


    /**
     * Gets the tableItemType value for this SNMPServerInfo.
     * 
     * @return tableItemType
     */
    public int getTableItemType() {
        return tableItemType;
    }


    /**
     * Sets the tableItemType value for this SNMPServerInfo.
     * 
     * @param tableItemType
     */
    public void setTableItemType(int tableItemType) {
        this.tableItemType = tableItemType;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SNMPServerInfo)) return false;
        SNMPServerInfo other = (SNMPServerInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ID == other.getID() &&
            ((this.serverName==null && other.getServerName()==null) || 
             (this.serverName!=null &&
              this.serverName.equals(other.getServerName()))) &&
            ((this.serverIP==null && other.getServerIP()==null) || 
             (this.serverIP!=null &&
              this.serverIP.equals(other.getServerIP()))) &&
            this.serverPort == other.getServerPort() &&
            this.isUse == other.getIsUse() &&
            this.haveTrap == other.getHaveTrap() &&
            this.trapPort == other.getTrapPort() &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark()))) &&
            this.sysType == other.getSysType() &&
            ((this.tableName==null && other.getTableName()==null) || 
             (this.tableName!=null &&
              this.tableName.equals(other.getTableName()))) &&
            ((this.tableItemID==null && other.getTableItemID()==null) || 
             (this.tableItemID!=null &&
              this.tableItemID.equals(other.getTableItemID()))) &&
            this.tableItemType == other.getTableItemType();
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
        if (getServerName() != null) {
            _hashCode += getServerName().hashCode();
        }
        if (getServerIP() != null) {
            _hashCode += getServerIP().hashCode();
        }
        _hashCode += getServerPort();
        _hashCode += getIsUse();
        _hashCode += getHaveTrap();
        _hashCode += getTrapPort();
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        _hashCode += getSysType();
        if (getTableName() != null) {
            _hashCode += getTableName().hashCode();
        }
        if (getTableItemID() != null) {
            _hashCode += getTableItemID().hashCode();
        }
        _hashCode += getTableItemType();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SNMPServerInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "SNMPServerInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverIP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServerIP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServerPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isUse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IsUse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("haveTrap");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HaveTrap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trapPort");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TrapPort"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Remark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sysType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SysType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tableName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TableName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tableItemID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TableItemID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tableItemType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TableItemType"));
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

/**
 * SNMPBaseState.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class SNMPBaseState  implements java.io.Serializable {
    private int ID;

    private int serverID;

    private int isOnLine;

    private int CPUUserUse;

    private int CPUSysUse;

    private int CPUIdle;

    private int memTotal;

    private int memFree;

    private int memAllFree;

    private int diskTotal;

    private int diskFree;

    private int diskUse;

    private int diskUsePct;

    private String timeNow;

    private int other1;

    private String remark;

    public SNMPBaseState() {
    }

    public SNMPBaseState(
           int ID,
           int serverID,
           int isOnLine,
           int CPUUserUse,
           int CPUSysUse,
           int CPUIdle,
           int memTotal,
           int memFree,
           int memAllFree,
           int diskTotal,
           int diskFree,
           int diskUse,
           int diskUsePct,
           String timeNow,
           int other1,
           String remark) {
           this.ID = ID;
           this.serverID = serverID;
           this.isOnLine = isOnLine;
           this.CPUUserUse = CPUUserUse;
           this.CPUSysUse = CPUSysUse;
           this.CPUIdle = CPUIdle;
           this.memTotal = memTotal;
           this.memFree = memFree;
           this.memAllFree = memAllFree;
           this.diskTotal = diskTotal;
           this.diskFree = diskFree;
           this.diskUse = diskUse;
           this.diskUsePct = diskUsePct;
           this.timeNow = timeNow;
           this.other1 = other1;
           this.remark = remark;
    }


    /**
     * Gets the ID value for this SNMPBaseState.
     * 
     * @return ID
     */
    public int getID() {
        return ID;
    }


    /**
     * Sets the ID value for this SNMPBaseState.
     * 
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Gets the serverID value for this SNMPBaseState.
     * 
     * @return serverID
     */
    public int getServerID() {
        return serverID;
    }


    /**
     * Sets the serverID value for this SNMPBaseState.
     * 
     * @param serverID
     */
    public void setServerID(int serverID) {
        this.serverID = serverID;
    }


    /**
     * Gets the isOnLine value for this SNMPBaseState.
     * 
     * @return isOnLine
     */
    public int getIsOnLine() {
        return isOnLine;
    }


    /**
     * Sets the isOnLine value for this SNMPBaseState.
     * 
     * @param isOnLine
     */
    public void setIsOnLine(int isOnLine) {
        this.isOnLine = isOnLine;
    }


    /**
     * Gets the CPUUserUse value for this SNMPBaseState.
     * 
     * @return CPUUserUse
     */
    public int getCPUUserUse() {
        return CPUUserUse;
    }


    /**
     * Sets the CPUUserUse value for this SNMPBaseState.
     * 
     * @param CPUUserUse
     */
    public void setCPUUserUse(int CPUUserUse) {
        this.CPUUserUse = CPUUserUse;
    }


    /**
     * Gets the CPUSysUse value for this SNMPBaseState.
     * 
     * @return CPUSysUse
     */
    public int getCPUSysUse() {
        return CPUSysUse;
    }


    /**
     * Sets the CPUSysUse value for this SNMPBaseState.
     * 
     * @param CPUSysUse
     */
    public void setCPUSysUse(int CPUSysUse) {
        this.CPUSysUse = CPUSysUse;
    }


    /**
     * Gets the CPUIdle value for this SNMPBaseState.
     * 
     * @return CPUIdle
     */
    public int getCPUIdle() {
        return CPUIdle;
    }


    /**
     * Sets the CPUIdle value for this SNMPBaseState.
     * 
     * @param CPUIdle
     */
    public void setCPUIdle(int CPUIdle) {
        this.CPUIdle = CPUIdle;
    }


    /**
     * Gets the memTotal value for this SNMPBaseState.
     * 
     * @return memTotal
     */
    public int getMemTotal() {
        return memTotal;
    }


    /**
     * Sets the memTotal value for this SNMPBaseState.
     * 
     * @param memTotal
     */
    public void setMemTotal(int memTotal) {
        this.memTotal = memTotal;
    }


    /**
     * Gets the memFree value for this SNMPBaseState.
     * 
     * @return memFree
     */
    public int getMemFree() {
        return memFree;
    }


    /**
     * Sets the memFree value for this SNMPBaseState.
     * 
     * @param memFree
     */
    public void setMemFree(int memFree) {
        this.memFree = memFree;
    }


    /**
     * Gets the memAllFree value for this SNMPBaseState.
     * 
     * @return memAllFree
     */
    public int getMemAllFree() {
        return memAllFree;
    }


    /**
     * Sets the memAllFree value for this SNMPBaseState.
     * 
     * @param memAllFree
     */
    public void setMemAllFree(int memAllFree) {
        this.memAllFree = memAllFree;
    }


    /**
     * Gets the diskTotal value for this SNMPBaseState.
     * 
     * @return diskTotal
     */
    public int getDiskTotal() {
        return diskTotal;
    }


    /**
     * Sets the diskTotal value for this SNMPBaseState.
     * 
     * @param diskTotal
     */
    public void setDiskTotal(int diskTotal) {
        this.diskTotal = diskTotal;
    }


    /**
     * Gets the diskFree value for this SNMPBaseState.
     * 
     * @return diskFree
     */
    public int getDiskFree() {
        return diskFree;
    }


    /**
     * Sets the diskFree value for this SNMPBaseState.
     * 
     * @param diskFree
     */
    public void setDiskFree(int diskFree) {
        this.diskFree = diskFree;
    }


    /**
     * Gets the diskUse value for this SNMPBaseState.
     * 
     * @return diskUse
     */
    public int getDiskUse() {
        return diskUse;
    }


    /**
     * Sets the diskUse value for this SNMPBaseState.
     * 
     * @param diskUse
     */
    public void setDiskUse(int diskUse) {
        this.diskUse = diskUse;
    }


    /**
     * Gets the diskUsePct value for this SNMPBaseState.
     * 
     * @return diskUsePct
     */
    public int getDiskUsePct() {
        return diskUsePct;
    }


    /**
     * Sets the diskUsePct value for this SNMPBaseState.
     * 
     * @param diskUsePct
     */
    public void setDiskUsePct(int diskUsePct) {
        this.diskUsePct = diskUsePct;
    }


    /**
     * Gets the timeNow value for this SNMPBaseState.
     * 
     * @return timeNow
     */
    public String getTimeNow() {
        return timeNow;
    }


    /**
     * Sets the timeNow value for this SNMPBaseState.
     * 
     * @param timeNow
     */
    public void setTimeNow(String timeNow) {
        this.timeNow = timeNow;
    }


    /**
     * Gets the other1 value for this SNMPBaseState.
     * 
     * @return other1
     */
    public int getOther1() {
        return other1;
    }


    /**
     * Sets the other1 value for this SNMPBaseState.
     * 
     * @param other1
     */
    public void setOther1(int other1) {
        this.other1 = other1;
    }


    /**
     * Gets the remark value for this SNMPBaseState.
     * 
     * @return remark
     */
    public String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this SNMPBaseState.
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof SNMPBaseState)) return false;
        SNMPBaseState other = (SNMPBaseState) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ID == other.getID() &&
            this.serverID == other.getServerID() &&
            this.isOnLine == other.getIsOnLine() &&
            this.CPUUserUse == other.getCPUUserUse() &&
            this.CPUSysUse == other.getCPUSysUse() &&
            this.CPUIdle == other.getCPUIdle() &&
            this.memTotal == other.getMemTotal() &&
            this.memFree == other.getMemFree() &&
            this.memAllFree == other.getMemAllFree() &&
            this.diskTotal == other.getDiskTotal() &&
            this.diskFree == other.getDiskFree() &&
            this.diskUse == other.getDiskUse() &&
            this.diskUsePct == other.getDiskUsePct() &&
            ((this.timeNow==null && other.getTimeNow()==null) || 
             (this.timeNow!=null &&
              this.timeNow.equals(other.getTimeNow()))) &&
            this.other1 == other.getOther1() &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark())));
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
        _hashCode += getServerID();
        _hashCode += getIsOnLine();
        _hashCode += getCPUUserUse();
        _hashCode += getCPUSysUse();
        _hashCode += getCPUIdle();
        _hashCode += getMemTotal();
        _hashCode += getMemFree();
        _hashCode += getMemAllFree();
        _hashCode += getDiskTotal();
        _hashCode += getDiskFree();
        _hashCode += getDiskUse();
        _hashCode += getDiskUsePct();
        if (getTimeNow() != null) {
            _hashCode += getTimeNow().hashCode();
        }
        _hashCode += getOther1();
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SNMPBaseState.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "SNMPBaseState"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serverID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServerID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isOnLine");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IsOnLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CPUUserUse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CPUUserUse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CPUSysUse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CPUSysUse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CPUIdle");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CPUIdle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MemTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memFree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MemFree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memAllFree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MemAllFree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diskTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DiskTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diskFree");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DiskFree"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diskUse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DiskUse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("diskUsePct");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DiskUsePct"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeNow");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TimeNow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("other1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Other1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("", "remark"));
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

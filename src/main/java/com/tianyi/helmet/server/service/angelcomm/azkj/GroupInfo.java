/**
 * GroupInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class GroupInfo  implements java.io.Serializable {
    private int id;

    private String groupName;

    private int theState;

    private String addTime;

    private String modifyTime;

    private String parentGroupNum;

    private String groupNum;

    private int groupLevel;

    private double latitude;

    private double longitude;

    private int intercomIdleTimeout;

    private int intercomPoweridleTimeout;

    private int intercomPowerTimecount;

    private int queueMaxNum;

    private int intercomHeartbeatSpace;

    private int intercomStateSpace;

    private String ip;

    private org.apache.axis.types.UnsignedInt port;

    private int seekCallSpace;

    private int ipHeartbeatSpace;

    private int ipStateSpace;

    private int theType;

    private int isRecord;

    private int isVideo;

    private String dnsprefix;

    public GroupInfo() {
    }

    public GroupInfo(
           int id,
           String groupName,
           int theState,
           String addTime,
           String modifyTime,
           String parentGroupNum,
           String groupNum,
           int groupLevel,
           double latitude,
           double longitude,
           int intercomIdleTimeout,
           int intercomPoweridleTimeout,
           int intercomPowerTimecount,
           int queueMaxNum,
           int intercomHeartbeatSpace,
           int intercomStateSpace,
           String ip,
           org.apache.axis.types.UnsignedInt port,
           int seekCallSpace,
           int ipHeartbeatSpace,
           int ipStateSpace,
           int theType,
           int isRecord,
           int isVideo,
           String dnsprefix) {
           this.id = id;
           this.groupName = groupName;
           this.theState = theState;
           this.addTime = addTime;
           this.modifyTime = modifyTime;
           this.parentGroupNum = parentGroupNum;
           this.groupNum = groupNum;
           this.groupLevel = groupLevel;
           this.latitude = latitude;
           this.longitude = longitude;
           this.intercomIdleTimeout = intercomIdleTimeout;
           this.intercomPoweridleTimeout = intercomPoweridleTimeout;
           this.intercomPowerTimecount = intercomPowerTimecount;
           this.queueMaxNum = queueMaxNum;
           this.intercomHeartbeatSpace = intercomHeartbeatSpace;
           this.intercomStateSpace = intercomStateSpace;
           this.ip = ip;
           this.port = port;
           this.seekCallSpace = seekCallSpace;
           this.ipHeartbeatSpace = ipHeartbeatSpace;
           this.ipStateSpace = ipStateSpace;
           this.theType = theType;
           this.isRecord = isRecord;
           this.isVideo = isVideo;
           this.dnsprefix = dnsprefix;
    }


    /**
     * Gets the id value for this GroupInfo.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this GroupInfo.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the groupName value for this GroupInfo.
     * 
     * @return groupName
     */
    public String getGroupName() {
        return groupName;
    }


    /**
     * Sets the groupName value for this GroupInfo.
     * 
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


    /**
     * Gets the theState value for this GroupInfo.
     * 
     * @return theState
     */
    public int getTheState() {
        return theState;
    }


    /**
     * Sets the theState value for this GroupInfo.
     * 
     * @param theState
     */
    public void setTheState(int theState) {
        this.theState = theState;
    }


    /**
     * Gets the addTime value for this GroupInfo.
     * 
     * @return addTime
     */
    public String getAddTime() {
        return addTime;
    }


    /**
     * Sets the addTime value for this GroupInfo.
     * 
     * @param addTime
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }


    /**
     * Gets the modifyTime value for this GroupInfo.
     * 
     * @return modifyTime
     */
    public String getModifyTime() {
        return modifyTime;
    }


    /**
     * Sets the modifyTime value for this GroupInfo.
     * 
     * @param modifyTime
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }


    /**
     * Gets the parentGroupNum value for this GroupInfo.
     * 
     * @return parentGroupNum
     */
    public String getParentGroupNum() {
        return parentGroupNum;
    }


    /**
     * Sets the parentGroupNum value for this GroupInfo.
     * 
     * @param parentGroupNum
     */
    public void setParentGroupNum(String parentGroupNum) {
        this.parentGroupNum = parentGroupNum;
    }


    /**
     * Gets the groupNum value for this GroupInfo.
     * 
     * @return groupNum
     */
    public String getGroupNum() {
        return groupNum;
    }


    /**
     * Sets the groupNum value for this GroupInfo.
     * 
     * @param groupNum
     */
    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }


    /**
     * Gets the groupLevel value for this GroupInfo.
     * 
     * @return groupLevel
     */
    public int getGroupLevel() {
        return groupLevel;
    }


    /**
     * Sets the groupLevel value for this GroupInfo.
     * 
     * @param groupLevel
     */
    public void setGroupLevel(int groupLevel) {
        this.groupLevel = groupLevel;
    }


    /**
     * Gets the latitude value for this GroupInfo.
     * 
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }


    /**
     * Sets the latitude value for this GroupInfo.
     * 
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    /**
     * Gets the longitude value for this GroupInfo.
     * 
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }


    /**
     * Sets the longitude value for this GroupInfo.
     * 
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    /**
     * Gets the intercomIdleTimeout value for this GroupInfo.
     * 
     * @return intercomIdleTimeout
     */
    public int getIntercomIdleTimeout() {
        return intercomIdleTimeout;
    }


    /**
     * Sets the intercomIdleTimeout value for this GroupInfo.
     * 
     * @param intercomIdleTimeout
     */
    public void setIntercomIdleTimeout(int intercomIdleTimeout) {
        this.intercomIdleTimeout = intercomIdleTimeout;
    }


    /**
     * Gets the intercomPoweridleTimeout value for this GroupInfo.
     * 
     * @return intercomPoweridleTimeout
     */
    public int getIntercomPoweridleTimeout() {
        return intercomPoweridleTimeout;
    }


    /**
     * Sets the intercomPoweridleTimeout value for this GroupInfo.
     * 
     * @param intercomPoweridleTimeout
     */
    public void setIntercomPoweridleTimeout(int intercomPoweridleTimeout) {
        this.intercomPoweridleTimeout = intercomPoweridleTimeout;
    }


    /**
     * Gets the intercomPowerTimecount value for this GroupInfo.
     * 
     * @return intercomPowerTimecount
     */
    public int getIntercomPowerTimecount() {
        return intercomPowerTimecount;
    }


    /**
     * Sets the intercomPowerTimecount value for this GroupInfo.
     * 
     * @param intercomPowerTimecount
     */
    public void setIntercomPowerTimecount(int intercomPowerTimecount) {
        this.intercomPowerTimecount = intercomPowerTimecount;
    }


    /**
     * Gets the queueMaxNum value for this GroupInfo.
     * 
     * @return queueMaxNum
     */
    public int getQueueMaxNum() {
        return queueMaxNum;
    }


    /**
     * Sets the queueMaxNum value for this GroupInfo.
     * 
     * @param queueMaxNum
     */
    public void setQueueMaxNum(int queueMaxNum) {
        this.queueMaxNum = queueMaxNum;
    }


    /**
     * Gets the intercomHeartbeatSpace value for this GroupInfo.
     * 
     * @return intercomHeartbeatSpace
     */
    public int getIntercomHeartbeatSpace() {
        return intercomHeartbeatSpace;
    }


    /**
     * Sets the intercomHeartbeatSpace value for this GroupInfo.
     * 
     * @param intercomHeartbeatSpace
     */
    public void setIntercomHeartbeatSpace(int intercomHeartbeatSpace) {
        this.intercomHeartbeatSpace = intercomHeartbeatSpace;
    }


    /**
     * Gets the intercomStateSpace value for this GroupInfo.
     * 
     * @return intercomStateSpace
     */
    public int getIntercomStateSpace() {
        return intercomStateSpace;
    }


    /**
     * Sets the intercomStateSpace value for this GroupInfo.
     * 
     * @param intercomStateSpace
     */
    public void setIntercomStateSpace(int intercomStateSpace) {
        this.intercomStateSpace = intercomStateSpace;
    }


    /**
     * Gets the ip value for this GroupInfo.
     * 
     * @return ip
     */
    public String getIp() {
        return ip;
    }


    /**
     * Sets the ip value for this GroupInfo.
     * 
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }


    /**
     * Gets the port value for this GroupInfo.
     * 
     * @return port
     */
    public org.apache.axis.types.UnsignedInt getPort() {
        return port;
    }


    /**
     * Sets the port value for this GroupInfo.
     * 
     * @param port
     */
    public void setPort(org.apache.axis.types.UnsignedInt port) {
        this.port = port;
    }


    /**
     * Gets the seekCallSpace value for this GroupInfo.
     * 
     * @return seekCallSpace
     */
    public int getSeekCallSpace() {
        return seekCallSpace;
    }


    /**
     * Sets the seekCallSpace value for this GroupInfo.
     * 
     * @param seekCallSpace
     */
    public void setSeekCallSpace(int seekCallSpace) {
        this.seekCallSpace = seekCallSpace;
    }


    /**
     * Gets the ipHeartbeatSpace value for this GroupInfo.
     * 
     * @return ipHeartbeatSpace
     */
    public int getIpHeartbeatSpace() {
        return ipHeartbeatSpace;
    }


    /**
     * Sets the ipHeartbeatSpace value for this GroupInfo.
     * 
     * @param ipHeartbeatSpace
     */
    public void setIpHeartbeatSpace(int ipHeartbeatSpace) {
        this.ipHeartbeatSpace = ipHeartbeatSpace;
    }


    /**
     * Gets the ipStateSpace value for this GroupInfo.
     * 
     * @return ipStateSpace
     */
    public int getIpStateSpace() {
        return ipStateSpace;
    }


    /**
     * Sets the ipStateSpace value for this GroupInfo.
     * 
     * @param ipStateSpace
     */
    public void setIpStateSpace(int ipStateSpace) {
        this.ipStateSpace = ipStateSpace;
    }


    /**
     * Gets the theType value for this GroupInfo.
     * 
     * @return theType
     */
    public int getTheType() {
        return theType;
    }


    /**
     * Sets the theType value for this GroupInfo.
     * 
     * @param theType
     */
    public void setTheType(int theType) {
        this.theType = theType;
    }


    /**
     * Gets the isRecord value for this GroupInfo.
     * 
     * @return isRecord
     */
    public int getIsRecord() {
        return isRecord;
    }


    /**
     * Sets the isRecord value for this GroupInfo.
     * 
     * @param isRecord
     */
    public void setIsRecord(int isRecord) {
        this.isRecord = isRecord;
    }


    /**
     * Gets the isVideo value for this GroupInfo.
     * 
     * @return isVideo
     */
    public int getIsVideo() {
        return isVideo;
    }


    /**
     * Sets the isVideo value for this GroupInfo.
     * 
     * @param isVideo
     */
    public void setIsVideo(int isVideo) {
        this.isVideo = isVideo;
    }


    /**
     * Gets the dnsprefix value for this GroupInfo.
     * 
     * @return dnsprefix
     */
    public String getDnsprefix() {
        return dnsprefix;
    }


    /**
     * Sets the dnsprefix value for this GroupInfo.
     * 
     * @param dnsprefix
     */
    public void setDnsprefix(String dnsprefix) {
        this.dnsprefix = dnsprefix;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GroupInfo)) return false;
        GroupInfo other = (GroupInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.id == other.getId() &&
            ((this.groupName==null && other.getGroupName()==null) || 
             (this.groupName!=null &&
              this.groupName.equals(other.getGroupName()))) &&
            this.theState == other.getTheState() &&
            ((this.addTime==null && other.getAddTime()==null) || 
             (this.addTime!=null &&
              this.addTime.equals(other.getAddTime()))) &&
            ((this.modifyTime==null && other.getModifyTime()==null) || 
             (this.modifyTime!=null &&
              this.modifyTime.equals(other.getModifyTime()))) &&
            ((this.parentGroupNum==null && other.getParentGroupNum()==null) || 
             (this.parentGroupNum!=null &&
              this.parentGroupNum.equals(other.getParentGroupNum()))) &&
            ((this.groupNum==null && other.getGroupNum()==null) || 
             (this.groupNum!=null &&
              this.groupNum.equals(other.getGroupNum()))) &&
            this.groupLevel == other.getGroupLevel() &&
            this.latitude == other.getLatitude() &&
            this.longitude == other.getLongitude() &&
            this.intercomIdleTimeout == other.getIntercomIdleTimeout() &&
            this.intercomPoweridleTimeout == other.getIntercomPoweridleTimeout() &&
            this.intercomPowerTimecount == other.getIntercomPowerTimecount() &&
            this.queueMaxNum == other.getQueueMaxNum() &&
            this.intercomHeartbeatSpace == other.getIntercomHeartbeatSpace() &&
            this.intercomStateSpace == other.getIntercomStateSpace() &&
            ((this.ip==null && other.getIp()==null) || 
             (this.ip!=null &&
              this.ip.equals(other.getIp()))) &&
            ((this.port==null && other.getPort()==null) || 
             (this.port!=null &&
              this.port.equals(other.getPort()))) &&
            this.seekCallSpace == other.getSeekCallSpace() &&
            this.ipHeartbeatSpace == other.getIpHeartbeatSpace() &&
            this.ipStateSpace == other.getIpStateSpace() &&
            this.theType == other.getTheType() &&
            this.isRecord == other.getIsRecord() &&
            this.isVideo == other.getIsVideo() &&
            ((this.dnsprefix==null && other.getDnsprefix()==null) || 
             (this.dnsprefix!=null &&
              this.dnsprefix.equals(other.getDnsprefix())));
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
        if (getGroupName() != null) {
            _hashCode += getGroupName().hashCode();
        }
        _hashCode += getTheState();
        if (getAddTime() != null) {
            _hashCode += getAddTime().hashCode();
        }
        if (getModifyTime() != null) {
            _hashCode += getModifyTime().hashCode();
        }
        if (getParentGroupNum() != null) {
            _hashCode += getParentGroupNum().hashCode();
        }
        if (getGroupNum() != null) {
            _hashCode += getGroupNum().hashCode();
        }
        _hashCode += getGroupLevel();
        _hashCode += new Double(getLatitude()).hashCode();
        _hashCode += new Double(getLongitude()).hashCode();
        _hashCode += getIntercomIdleTimeout();
        _hashCode += getIntercomPoweridleTimeout();
        _hashCode += getIntercomPowerTimecount();
        _hashCode += getQueueMaxNum();
        _hashCode += getIntercomHeartbeatSpace();
        _hashCode += getIntercomStateSpace();
        if (getIp() != null) {
            _hashCode += getIp().hashCode();
        }
        if (getPort() != null) {
            _hashCode += getPort().hashCode();
        }
        _hashCode += getSeekCallSpace();
        _hashCode += getIpHeartbeatSpace();
        _hashCode += getIpStateSpace();
        _hashCode += getTheType();
        _hashCode += getIsRecord();
        _hashCode += getIsVideo();
        if (getDnsprefix() != null) {
            _hashCode += getDnsprefix().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GroupInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "groupInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group-name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theState");
        elemField.setXmlName(new javax.xml.namespace.QName("", "the-state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "add-time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifyTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "modify-time"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentGroupNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parent-group-num"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group-num"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "group-level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latitude");
        elemField.setXmlName(new javax.xml.namespace.QName("", "latitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("longitude");
        elemField.setXmlName(new javax.xml.namespace.QName("", "longitude"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intercomIdleTimeout");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intercom-idle-timeout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intercomPoweridleTimeout");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intercom-poweridle-timeout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intercomPowerTimecount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intercom-power-timecount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queueMaxNum");
        elemField.setXmlName(new javax.xml.namespace.QName("", "queue-max-num"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intercomHeartbeatSpace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intercom-heartbeat-space"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intercomStateSpace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "intercom-state-space"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ip");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("port");
        elemField.setXmlName(new javax.xml.namespace.QName("", "port"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedInt"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("seekCallSpace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "seek-call-space"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipHeartbeatSpace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ip-heartbeat-space"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ipStateSpace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ip-state-space"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("theType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "the-type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRecord");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is-record"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isVideo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "is-video"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dnsprefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dnsprefix"));
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

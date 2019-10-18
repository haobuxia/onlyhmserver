/**
 * CallRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj;

public class CallRecord  implements java.io.Serializable {
    private int ID;

    private String cid;

    private String caller;

    private String called;

    private String callername;

    private String calledname;

    private String callerdnsprefix;

    private String calleddnsprefix;

    private String groupcid;

    private String frontcid1;

    private String frontcid2;

    private String remark;

    private String recordname;

    private String subpath;

    private String createtime;

    private String releasetime;

    private int timelength;

    private int recordtype;

    private String webpath;

    private int filetype;

    public CallRecord() {
    }

    public CallRecord(
           int ID,
           String cid,
           String caller,
           String called,
           String callername,
           String calledname,
           String callerdnsprefix,
           String calleddnsprefix,
           String groupcid,
           String frontcid1,
           String frontcid2,
           String remark,
           String recordname,
           String subpath,
           String createtime,
           String releasetime,
           int timelength,
           int recordtype,
           String webpath,
           int filetype) {
           this.ID = ID;
           this.cid = cid;
           this.caller = caller;
           this.called = called;
           this.callername = callername;
           this.calledname = calledname;
           this.callerdnsprefix = callerdnsprefix;
           this.calleddnsprefix = calleddnsprefix;
           this.groupcid = groupcid;
           this.frontcid1 = frontcid1;
           this.frontcid2 = frontcid2;
           this.remark = remark;
           this.recordname = recordname;
           this.subpath = subpath;
           this.createtime = createtime;
           this.releasetime = releasetime;
           this.timelength = timelength;
           this.recordtype = recordtype;
           this.webpath = webpath;
           this.filetype = filetype;
    }


    /**
     * Gets the ID value for this CallRecord.
     * 
     * @return ID
     */
    public int getID() {
        return ID;
    }


    /**
     * Sets the ID value for this CallRecord.
     * 
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }


    /**
     * Gets the cid value for this CallRecord.
     * 
     * @return cid
     */
    public String getCid() {
        return cid;
    }


    /**
     * Sets the cid value for this CallRecord.
     * 
     * @param cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }


    /**
     * Gets the caller value for this CallRecord.
     * 
     * @return caller
     */
    public String getCaller() {
        return caller;
    }


    /**
     * Sets the caller value for this CallRecord.
     * 
     * @param caller
     */
    public void setCaller(String caller) {
        this.caller = caller;
    }


    /**
     * Gets the called value for this CallRecord.
     * 
     * @return called
     */
    public String getCalled() {
        return called;
    }


    /**
     * Sets the called value for this CallRecord.
     * 
     * @param called
     */
    public void setCalled(String called) {
        this.called = called;
    }


    /**
     * Gets the callername value for this CallRecord.
     * 
     * @return callername
     */
    public String getCallername() {
        return callername;
    }


    /**
     * Sets the callername value for this CallRecord.
     * 
     * @param callername
     */
    public void setCallername(String callername) {
        this.callername = callername;
    }


    /**
     * Gets the calledname value for this CallRecord.
     * 
     * @return calledname
     */
    public String getCalledname() {
        return calledname;
    }


    /**
     * Sets the calledname value for this CallRecord.
     * 
     * @param calledname
     */
    public void setCalledname(String calledname) {
        this.calledname = calledname;
    }


    /**
     * Gets the callerdnsprefix value for this CallRecord.
     * 
     * @return callerdnsprefix
     */
    public String getCallerdnsprefix() {
        return callerdnsprefix;
    }


    /**
     * Sets the callerdnsprefix value for this CallRecord.
     * 
     * @param callerdnsprefix
     */
    public void setCallerdnsprefix(String callerdnsprefix) {
        this.callerdnsprefix = callerdnsprefix;
    }


    /**
     * Gets the calleddnsprefix value for this CallRecord.
     * 
     * @return calleddnsprefix
     */
    public String getCalleddnsprefix() {
        return calleddnsprefix;
    }


    /**
     * Sets the calleddnsprefix value for this CallRecord.
     * 
     * @param calleddnsprefix
     */
    public void setCalleddnsprefix(String calleddnsprefix) {
        this.calleddnsprefix = calleddnsprefix;
    }


    /**
     * Gets the groupcid value for this CallRecord.
     * 
     * @return groupcid
     */
    public String getGroupcid() {
        return groupcid;
    }


    /**
     * Sets the groupcid value for this CallRecord.
     * 
     * @param groupcid
     */
    public void setGroupcid(String groupcid) {
        this.groupcid = groupcid;
    }


    /**
     * Gets the frontcid1 value for this CallRecord.
     * 
     * @return frontcid1
     */
    public String getFrontcid1() {
        return frontcid1;
    }


    /**
     * Sets the frontcid1 value for this CallRecord.
     * 
     * @param frontcid1
     */
    public void setFrontcid1(String frontcid1) {
        this.frontcid1 = frontcid1;
    }


    /**
     * Gets the frontcid2 value for this CallRecord.
     * 
     * @return frontcid2
     */
    public String getFrontcid2() {
        return frontcid2;
    }


    /**
     * Sets the frontcid2 value for this CallRecord.
     * 
     * @param frontcid2
     */
    public void setFrontcid2(String frontcid2) {
        this.frontcid2 = frontcid2;
    }


    /**
     * Gets the remark value for this CallRecord.
     * 
     * @return remark
     */
    public String getRemark() {
        return remark;
    }


    /**
     * Sets the remark value for this CallRecord.
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /**
     * Gets the recordname value for this CallRecord.
     * 
     * @return recordname
     */
    public String getRecordname() {
        return recordname;
    }


    /**
     * Sets the recordname value for this CallRecord.
     * 
     * @param recordname
     */
    public void setRecordname(String recordname) {
        this.recordname = recordname;
    }


    /**
     * Gets the subpath value for this CallRecord.
     * 
     * @return subpath
     */
    public String getSubpath() {
        return subpath;
    }


    /**
     * Sets the subpath value for this CallRecord.
     * 
     * @param subpath
     */
    public void setSubpath(String subpath) {
        this.subpath = subpath;
    }


    /**
     * Gets the createtime value for this CallRecord.
     * 
     * @return createtime
     */
    public String getCreatetime() {
        return createtime;
    }


    /**
     * Sets the createtime value for this CallRecord.
     * 
     * @param createtime
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }


    /**
     * Gets the releasetime value for this CallRecord.
     * 
     * @return releasetime
     */
    public String getReleasetime() {
        return releasetime;
    }


    /**
     * Sets the releasetime value for this CallRecord.
     * 
     * @param releasetime
     */
    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }


    /**
     * Gets the timelength value for this CallRecord.
     * 
     * @return timelength
     */
    public int getTimelength() {
        return timelength;
    }


    /**
     * Sets the timelength value for this CallRecord.
     * 
     * @param timelength
     */
    public void setTimelength(int timelength) {
        this.timelength = timelength;
    }


    /**
     * Gets the recordtype value for this CallRecord.
     * 
     * @return recordtype
     */
    public int getRecordtype() {
        return recordtype;
    }


    /**
     * Sets the recordtype value for this CallRecord.
     * 
     * @param recordtype
     */
    public void setRecordtype(int recordtype) {
        this.recordtype = recordtype;
    }


    /**
     * Gets the webpath value for this CallRecord.
     * 
     * @return webpath
     */
    public String getWebpath() {
        return webpath;
    }


    /**
     * Sets the webpath value for this CallRecord.
     * 
     * @param webpath
     */
    public void setWebpath(String webpath) {
        this.webpath = webpath;
    }


    /**
     * Gets the filetype value for this CallRecord.
     * 
     * @return filetype
     */
    public int getFiletype() {
        return filetype;
    }


    /**
     * Sets the filetype value for this CallRecord.
     * 
     * @param filetype
     */
    public void setFiletype(int filetype) {
        this.filetype = filetype;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof CallRecord)) return false;
        CallRecord other = (CallRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.ID == other.getID() &&
            ((this.cid==null && other.getCid()==null) || 
             (this.cid!=null &&
              this.cid.equals(other.getCid()))) &&
            ((this.caller==null && other.getCaller()==null) || 
             (this.caller!=null &&
              this.caller.equals(other.getCaller()))) &&
            ((this.called==null && other.getCalled()==null) || 
             (this.called!=null &&
              this.called.equals(other.getCalled()))) &&
            ((this.callername==null && other.getCallername()==null) || 
             (this.callername!=null &&
              this.callername.equals(other.getCallername()))) &&
            ((this.calledname==null && other.getCalledname()==null) || 
             (this.calledname!=null &&
              this.calledname.equals(other.getCalledname()))) &&
            ((this.callerdnsprefix==null && other.getCallerdnsprefix()==null) || 
             (this.callerdnsprefix!=null &&
              this.callerdnsprefix.equals(other.getCallerdnsprefix()))) &&
            ((this.calleddnsprefix==null && other.getCalleddnsprefix()==null) || 
             (this.calleddnsprefix!=null &&
              this.calleddnsprefix.equals(other.getCalleddnsprefix()))) &&
            ((this.groupcid==null && other.getGroupcid()==null) || 
             (this.groupcid!=null &&
              this.groupcid.equals(other.getGroupcid()))) &&
            ((this.frontcid1==null && other.getFrontcid1()==null) || 
             (this.frontcid1!=null &&
              this.frontcid1.equals(other.getFrontcid1()))) &&
            ((this.frontcid2==null && other.getFrontcid2()==null) || 
             (this.frontcid2!=null &&
              this.frontcid2.equals(other.getFrontcid2()))) &&
            ((this.remark==null && other.getRemark()==null) || 
             (this.remark!=null &&
              this.remark.equals(other.getRemark()))) &&
            ((this.recordname==null && other.getRecordname()==null) || 
             (this.recordname!=null &&
              this.recordname.equals(other.getRecordname()))) &&
            ((this.subpath==null && other.getSubpath()==null) || 
             (this.subpath!=null &&
              this.subpath.equals(other.getSubpath()))) &&
            ((this.createtime==null && other.getCreatetime()==null) || 
             (this.createtime!=null &&
              this.createtime.equals(other.getCreatetime()))) &&
            ((this.releasetime==null && other.getReleasetime()==null) || 
             (this.releasetime!=null &&
              this.releasetime.equals(other.getReleasetime()))) &&
            this.timelength == other.getTimelength() &&
            this.recordtype == other.getRecordtype() &&
            ((this.webpath==null && other.getWebpath()==null) || 
             (this.webpath!=null &&
              this.webpath.equals(other.getWebpath()))) &&
            this.filetype == other.getFiletype();
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
        if (getCid() != null) {
            _hashCode += getCid().hashCode();
        }
        if (getCaller() != null) {
            _hashCode += getCaller().hashCode();
        }
        if (getCalled() != null) {
            _hashCode += getCalled().hashCode();
        }
        if (getCallername() != null) {
            _hashCode += getCallername().hashCode();
        }
        if (getCalledname() != null) {
            _hashCode += getCalledname().hashCode();
        }
        if (getCallerdnsprefix() != null) {
            _hashCode += getCallerdnsprefix().hashCode();
        }
        if (getCalleddnsprefix() != null) {
            _hashCode += getCalleddnsprefix().hashCode();
        }
        if (getGroupcid() != null) {
            _hashCode += getGroupcid().hashCode();
        }
        if (getFrontcid1() != null) {
            _hashCode += getFrontcid1().hashCode();
        }
        if (getFrontcid2() != null) {
            _hashCode += getFrontcid2().hashCode();
        }
        if (getRemark() != null) {
            _hashCode += getRemark().hashCode();
        }
        if (getRecordname() != null) {
            _hashCode += getRecordname().hashCode();
        }
        if (getSubpath() != null) {
            _hashCode += getSubpath().hashCode();
        }
        if (getCreatetime() != null) {
            _hashCode += getCreatetime().hashCode();
        }
        if (getReleasetime() != null) {
            _hashCode += getReleasetime().hashCode();
        }
        _hashCode += getTimelength();
        _hashCode += getRecordtype();
        if (getWebpath() != null) {
            _hashCode += getWebpath().hashCode();
        }
        _hashCode += getFiletype();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CallRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:azkj", "CallRecord"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Cid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caller");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Caller"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("called");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Called"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callername");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Callername"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calledname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Calledname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("callerdnsprefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Callerdnsprefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calleddnsprefix");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Calleddnsprefix"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupcid");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Groupcid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frontcid1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Frontcid1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frontcid2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Frontcid2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remark");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Remark"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordname");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Recordname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subpath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Subpath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createtime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Createtime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releasetime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Releasetime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timelength");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Timelength"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recordtype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Recordtype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webpath");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Webpath"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filetype");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Filetype"));
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

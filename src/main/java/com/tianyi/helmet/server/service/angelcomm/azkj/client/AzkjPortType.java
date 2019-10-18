/**
 * AzkjPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tianyi.helmet.server.service.angelcomm.azkj.client;

import com.tianyi.helmet.server.service.angelcomm.azkj.*;
import com.tianyi.helmet.server.service.angelcomm.azkj.holders.ArrayOfCallLogHolder;
import com.tianyi.helmet.server.service.angelcomm.azkj.holders.ArrayOfCallRecordHolder;

public interface AzkjPortType extends java.rmi.Remote {

    /**
     * Service definition of function ns__GetEmployeeListAll
     */
    public EmployeeInfo[] getEmployeeListAll() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetEmployeeListByGroupNumber
     */
    public EmployeeInfo[] getEmployeeListByGroupNumber(String groupNumber) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetEmployeeInfoByNumber
     */
    public EmployeeInfo getEmployeeInfoByNumber(String number) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CreateEmployeeInfo
     */
    public int createEmployeeInfo(String number, String name, int level, String pwd, int typ, double lon, double lat, int recordS, int recordV, String remark) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateEmployeeInfo
     */
    public int updateEmployeeInfo(String number, String name, int level, String pwd, int typ, double lon, double lat, int recordS, int recordV, String remark) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteEmployeeInfo
     */
    public int deleteEmployeeInfo(String number) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetEmployeeLastLoginTime
     */
    public String getEmployeeLastLoginTime(String number) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetGroupListAll
     */
    public GroupInfo[] getGroupListAll() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetGroupInfoByNumber
     */
    public GroupInfo getGroupInfoByNumber(String number) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CreateGroupInfo
     */
    public int createGroupInfo(String groupName, String groupNum, String parentGroupNum, int theType, int level, int intercomIdleTimeOut, int intercomPowerIdleTimeOut, int intercomPowerTimeCount, int queueMaxNum, int intercomHeartBeatSpace, int intercomStateSpace, int recordS, int recordV) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateGroupInfo
     */
    public int updateGroupInfo(String groupName, String groupNum, String parentGroupNum, int theType, int level, int intercomIdleTimeOut, int intercomPowerIdleTimeOut, int intercomPowerTimeCount, int queueMaxNum, int intercomHeartBeatSpace, int intercomStateSpace, int recordS, int recordV) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteGroupInfo
     */
    public int deleteGroupInfo(String number) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GroupAddEmployee
     */
    public int groupAddEmployee(String groupNumber, String employeeNumber) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GroupDeleteEmployee
     */
    public int groupDeleteEmployee(String groupNumber, String employeeNumber) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetAllOrderInfo
     */
    public CallOrderInfo[] getAllOrderInfo(boolean getdetail) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateOrderInfo
     */
    public int updateOrderInfo(CallOrderInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelOrderInfo
     */
    public int delOrderInfo(String called) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetOrderDetail
     */
    public CallOrderDetail[] getOrderDetail(String called) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetRelayInfo
     */
    public RelayInfo[] getRelayInfo(int id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CreateRelayInfo
     */
    public int createRelayInfo(RelayInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateRelayInfo
     */
    public int updateRelayInfo(RelayInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteRelayInfo
     */
    public int deleteRelayInfo(int id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetRouteInfos
     */
    public RouteInfo[] getRouteInfos(int id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CreateRouteInfo
     */
    public int createRouteInfo(RouteInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateRouteInfo
     */
    public int updateRouteInfo(RouteInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteRouteInfo
     */
    public int deleteRouteInfo(int id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetRouteRelayInfos
     */
    public RouteWithRelay[] getRouteRelayInfos(int routeid, int relayid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CreateRouteRelayInfo
     */
    public int createRouteRelayInfo(int routeid, int relayid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteRouteRelayInfo
     */
    public int deleteRouteRelayInfo(int routeid, int relayid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SelectCallLog
     */
    public void selectCallLog(int pageindex, int pagesize, String begintime, String endtime, int minlen, int maxlen, String number, int calltype, javax.xml.rpc.holders.IntHolder pagecount, ArrayOfCallLogHolder details) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteCallLog
     */
    public int deleteCallLog(String begintime, String endtime, int minlen, int maxlen, String number, int calltype, String cid, String groupcid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SelectCallRecord
     */
    public void selectCallRecord(int pageindex, int pagesize, String begintime, String endtime, int minlen, int maxlen, String number, int calltype, int recordtype, javax.xml.rpc.holders.IntHolder pagecount, ArrayOfCallRecordHolder details) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteCallRecord
     */
    public int deleteCallRecord(String begintime, String endtime, int minlen, int maxlen, String number, int calltype, String cid, String groupcid, int recordtype) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetSnmpServerInfo
     */
    public SNMPServerInfo[] getSnmpServerInfo() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CreateSnmpServerInfo
     */
    public int createSnmpServerInfo(SNMPServerInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateSnmpServerInfo
     */
    public int updateSnmpServerInfo(SNMPServerInfo info) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteSnmpServerInfo
     */
    public int deleteSnmpServerInfo(int id, String serviceip) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetSnmpStateInfos
     */
    public SNMPBaseState[] getSnmpStateInfos(int serverid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SelectSysParameter
     */
    public SysParameter[] selectSysParameter(String key) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__InsertSysParameter
     */
    public int insertSysParameter(String key, String val, int theState) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateSysParameter
     */
    public int updateSysParameter(String key, String val, int theState) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteSysParameter
     */
    public int deleteSysParameter(String key) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetPlatInfo
     */
    public PlatInfo[] getPlatInfo(int relayid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetPlatOrg
     */
    public PlatOrg[] getPlatOrg(int relayid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetPlatInfoByID
     */
    public PlatOrg[] getPlatInfoByID(int relayid, String orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetPlatSubOrg
     */
    public PlatOrg[] getPlatSubOrg(int relayid, String orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetPlatDevNumber
     */
    public PlatDevNumber[] getPlatDevNumber(int relayid, String orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SynchronDB
     */
    public int synchronDB(int relayid, String platid) throws java.rmi.RemoteException;
}

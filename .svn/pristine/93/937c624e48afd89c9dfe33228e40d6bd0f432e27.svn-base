package com.tianyi.helmet.server.entity.svc.po;

/**
 * 故障处理
 * Created by liuhanc on 2018/3/16.
 */
public class SvcFaultHandle extends SvcRwhAbstract {
    private boolean handled;
    //    private String notHandleAudioPath;
    private String notHandleReason;//未处理原因

    public SvcFaultHandle() {
    }

    public SvcFaultHandle(String rwh, boolean handled, String notHandleReason) {
        this.rwh = rwh;
        this.handled = handled;
        this.notHandleReason = notHandleReason;
    }

    public static SvcFaultHandle handled(String rwh) {
        return new SvcFaultHandle(rwh, true, null);
    }

    public static SvcFaultHandle notHandled(String rwh, String notHandleReason) {
        return new SvcFaultHandle(rwh, false, notHandleReason);
    }

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    public String getNotHandleReason() {
        return notHandleReason;
    }

    public void setNotHandleReason(String notHandleReason) {
        this.notHandleReason = notHandleReason;
    }

    @Override
    public String toString() {
        return "rwh=" + rwh + ",handled=" + handled + ",notHandleReason=" + notHandleReason;
    }

}

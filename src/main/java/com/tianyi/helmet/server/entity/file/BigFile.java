package com.tianyi.helmet.server.entity.file;

import com.tianyi.helmet.server.vo.DoubleVo;

import java.util.HashSet;
import java.util.Set;

/**
 * 大文件
 * 包含小片文件
 *
 * Created by liuhanc on 2017/12/27.
 */
public class BigFile extends File {
    private String fileId;
    private String machineCode;
    private int segmentCount;
    private String tag;
    private Set<DoubleVo<Integer,String>> segmentFileSet;

    public String getMachineCode() {
        return machineCode;
    }

    public void setMachineCode(String machineCode) {
        this.machineCode = machineCode;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSegmentExists(int seq){
        if(segmentFileSet == null){
            segmentFileSet = new HashSet<>();
        }

        if(segmentFileSet.stream().filter(vo->vo.getKey().intValue() == seq).findAny().isPresent()){
            //存在
            return true;
        }
        return false;
    }

    public boolean addSegment(int seq,String ossPath){
        boolean exists = isSegmentExists(seq);
        if(exists) return false;
        segmentFileSet.add(new DoubleVo<>(seq,ossPath));
        return true;
    }

    /**
     * 文件分片是否都已齐全
     * @return
     */
    public boolean isFileComplete(){
        if(segmentFileSet == null || segmentCount > segmentFileSet.size()) return  false;
        int realCount = (int)segmentFileSet.stream().map(DoubleVo::getKey).distinct().count();
        return (realCount == segmentCount);
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public int getSegmentCount() {
        return segmentCount;
    }

    public void setSegmentCount(int segmentCount) {
        this.segmentCount = segmentCount;
    }

    public Set<DoubleVo<Integer, String>> getSegmentFileSet() {
        return segmentFileSet;
    }

    public void setSegmentFileSet(Set<DoubleVo<Integer, String>> segmentFileSet) {
        this.segmentFileSet = segmentFileSet;
    }
}

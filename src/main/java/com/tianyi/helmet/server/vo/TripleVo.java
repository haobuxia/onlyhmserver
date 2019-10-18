package com.tianyi.helmet.server.vo;

/**
 * 三个数据的存储
 * Created by liuhanc on 2017/11/9.
 */
public class TripleVo<ON,TW,TH> {
    private ON one;
    private TW two;
    private TH three;

    public TripleVo(){}
    public TripleVo(ON o1,TW o2){
        this.one = o1;
        this.two = o2;
    }
    public TripleVo(ON o1,TW o2,TH o3){
        this.one = o1;
        this.two = o2;
        this.three = o3;
    }

    public ON getOne() {
        return one;
    }

    public void setOne(ON one) {
        this.one = one;
    }

    public TW getTwo() {
        return two;
    }

    public void setTwo(TW two) {
        this.two = two;
    }

    public TH getThree() {
        return three;
    }

    public void setThree(TH three) {
        this.three = three;
    }
}

package com.xinyou.dome.entity;

/**
 * @Author ：chenxinyou.
 * @Title :
 * @Date ：Created in 2019/5/6 16:03
 * @Description:
 */
public class SequenceEntity {
    public long id;
    public long maxValue;
    public long step;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }

    public long getStep() {
        return step;
    }

    public void setStep(long step) {
        this.step = step;
    }
}

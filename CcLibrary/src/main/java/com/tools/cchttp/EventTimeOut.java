package com.tools.cchttp;

/**
 * @author AlienChao
 * @date 2020/06/08 11:45
 */
public class EventTimeOut {

    private Throwable e;

    public EventTimeOut(Throwable e) {
        this.e = e;
    }

    public Throwable getE() {
        return e;
    }

    public void setE(Throwable e) {
        this.e = e;
    }
}

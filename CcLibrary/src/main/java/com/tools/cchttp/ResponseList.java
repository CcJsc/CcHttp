package com.tools.cchttp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AlienChao
 * @date 2019/11/21 11:17
 */
public class ResponseList<T> {

    private int CURRENTROWS;
    private String ROWS;
    private List<T> DATA;


    public int getCURRENTROWS() {
        return CURRENTROWS;
    }

    public void setCURRENTROWS(int CURRENTROWS) {
        this.CURRENTROWS = CURRENTROWS;
    }

    public String getROWS() {
        return ROWS == null ? "" : ROWS;
    }

    public void setROWS(String ROWS) {
        this.ROWS = ROWS == null ? "" : ROWS;
    }

    public List<T> getDATA() {
        if (DATA == null) {
            return new ArrayList<>();
        }
        return DATA;
    }


    public void setDATA(List<T> DATA) {
        this.DATA = DATA;
    }

}

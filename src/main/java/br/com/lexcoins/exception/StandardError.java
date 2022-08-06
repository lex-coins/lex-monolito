package br.com.lexcoins.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;

    public StandardError(Integer status, String msg, Long timeStamp) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStapm() {
        return timeStamp;
    }

    public void setTimeStapm(Long timeStapm) {
        this.timeStamp = timeStapm;
    }

    private Integer status;
    private String msg;
    private Long timeStamp;


}

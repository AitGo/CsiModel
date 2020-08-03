package com.liany.csiserverapp.network.Exception;

/**
 * @创建者 ly
 * @创建时间 2019/4/15
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class ApiException extends Exception{
    private int code;
    private String displayMessage;

    public ApiException(int code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

//    public ApiException(int code, String message, String displayMessage) {
//        super(message);
//        this.code = code;
//        this.displayMessage = displayMessage;
//    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

}

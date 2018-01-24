package junitTest;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dingqin
 * @date 2018/1/23
 */
public class LeaveModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String processInstaneceId;

    private String processDefineId;

    private String name;

    private int leaveDays;

    private String reason;

    private String nextAssignee;

    //涓嶆寔涔呭寲杩欎袱涓弬鏁�
    private transient Date startDate;

    private  transient Date endDate;

    public String getProcessInstaneceId() {
        return processInstaneceId;
    }

    public void setProcessInstaneceId(String processInstaneceId) {
        this.processInstaneceId = processInstaneceId;
    }

    public String getProcessDefineId() {
        return processDefineId;
    }

    public void setProcessDefineId(String processDefineId) {
        this.processDefineId = processDefineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaveDays() {
//        leaveDays = differentDaysByMillisecond(endDate,startDate);
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNextAssignee() {
        return nextAssignee;
    }

    public void setNextAssignee(String nextAssignee) {
        this.nextAssignee = nextAssignee;
    }

    /**
     * 閫氳繃鏃堕棿绉掓绉掓暟鍒ゆ柇涓や釜鏃堕棿鐨勯棿闅�
     * @param date1
     * @param date2
     * @return
     */
    private   int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }
}

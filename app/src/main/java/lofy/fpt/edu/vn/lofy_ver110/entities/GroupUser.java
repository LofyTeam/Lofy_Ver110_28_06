package lofy.fpt.edu.vn.lofy_ver110.entities;

public class GroupUser{
private String userId;
private String groupId;
private String userNickName;
private boolean isHost;
private  boolean isVice;
private String userColor;
private String timeStamp;
private double sizeRadius;
private String userStatus;

    public GroupUser() {
    }

    public GroupUser(String userId, String groupId, String userNickName, boolean isHost, boolean isVice, String userColor, String timeStamp, double sizeRadius, String userStatus) {
        this.userId = userId;
        this.groupId = groupId;
        this.userNickName = userNickName;
        this.isHost = isHost;
        this.isVice = isVice;
        this.userColor = userColor;
        this.timeStamp = timeStamp;
        this.sizeRadius = sizeRadius;
        this.userStatus = userStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public boolean isVice() {
        return isVice;
    }

    public void setVice(boolean vice) {
        isVice = vice;
    }

    public String getUserColor() {
        return userColor;
    }

    public void setUserColor(String userColor) {
        this.userColor = userColor;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getSizeRadius() {
        return sizeRadius;
    }

    public void setSizeRadius(double sizeRadius) {
        this.sizeRadius = sizeRadius;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}

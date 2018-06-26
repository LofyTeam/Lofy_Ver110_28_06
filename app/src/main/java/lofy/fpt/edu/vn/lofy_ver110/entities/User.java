package lofy.fpt.edu.vn.lofy_ver110.entities;

public class User {
    private String userId;
    private String fbId;
    private String userName;
    private int userAvatar;
    private String userPhone;
    private String userStatus;

    private double userLati;
    private double userLongti;
    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(int userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public double getUserLati() {
        return userLati;
    }

    public void setUserLati(double userLati) {
        this.userLati = userLati;
    }

    public double getUserLongti() {
        return userLongti;
    }

    public void setUserLongti(double userLongti) {
        this.userLongti = userLongti;
    }

    public User(String userId, String fbId, String userName, int userAvatar, String userPhone, String userStatus, double userLati, double userLongti) {

        this.userId = userId;
        this.fbId = fbId;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.userPhone = userPhone;
        this.userStatus = userStatus;
        this.userLati = userLati;
        this.userLongti = userLongti;
    }
}

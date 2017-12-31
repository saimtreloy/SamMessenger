package saim.com.sammessenger.Model;

/**
 * Created by NREL on 12/30/17.
 */

public class ModelUser {
    String nameDisplay, fullName, status ,email, mobile, gander, userImage, coverImage;

    public ModelUser(String nameDisplay, String fullName, String status, String email, String mobile, String gander, String userImage, String coverImage) {
        this.nameDisplay = nameDisplay;
        this.fullName = fullName;
        this.status = status;
        this.email = email;
        this.mobile = mobile;
        this.gander = gander;
        this.userImage = userImage;
        this.coverImage = coverImage;
    }

    public String getNameDisplay() {
        return nameDisplay;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGander() {
        return gander;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getCoverImage() {
        return coverImage;
    }
}

public class Upload {

    public String mImageUri;

    public String mUserName;

    public int mUserPhoneNumber;

    public Upload() {
    }

    public Upload(String mImageUri, String mUserName, int mUserPhoneNumber) {
        this.mImageUri = mImageUri;
        this.mUserName = mUserName;
        this.mUserPhoneNumber = mUserPhoneNumber;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public String getmUserName() {
        return mUserName;
    }

    public int getmUserPhoneNumber() {
        return mUserPhoneNumber;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setmUserPhoneNumber(int mUserPhoneNumber) {
        this.mUserPhoneNumber = mUserPhoneNumber;
    }
}


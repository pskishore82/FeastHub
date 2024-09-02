package psk.example.feasthub;

import android.net.Uri;

public class StringHolder {
    private static StringHolder instance;
    private String myString;
    private Uri myicon;

    private String mygmail;


    private StringHolder() {
        // private constructor to enforce singleton pattern
    }



    public static synchronized StringHolder getInstance() {
        if (instance == null) {
            instance = new StringHolder();
        }
        return instance;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public Uri getMyicon() {
        return myicon;
    }

    public void setMyicon(Uri myicon) {
        this.myicon = myicon;
    }
    public String getMygmail() {
        return mygmail;
    }

    public void setMygmail(String mygmail) {
        this.mygmail = mygmail;
    }



}
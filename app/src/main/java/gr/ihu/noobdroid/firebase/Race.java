package gr.ihu.noobdroid.firebase;

import com.google.firebase.firestore.Exclude;
import java.io.Serializable;

public class Race implements Serializable {

    @Exclude
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int sid;
    private String city;
    private String country;
    private String day;

    public Race(){
    }

    public Race(int sid, String city, String country, String day){
        this.sid = sid;
        this.city = city;
        this.country = country;
        this.day = day;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}

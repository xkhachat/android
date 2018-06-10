package xkhachat.android.xml.mendelu.cz.androidproject;

public class Events {
    private String Id;
    private String Name;
    private String Description;
    private String Longitude;
    private String Latitude;

    public Events(String id, String name, String description, String longitude, String latitude) {
        Id = id;
        Name = name;
        Description = description;
        Longitude = longitude;
        Latitude = latitude;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getLatitude() {
        return Latitude;
    }
}

package xmu.crms.view.vo;

public class LocationVO {
    private Double longitude;
    private Double latitude;
    private Double elevation;

    public LocationVO() {
    }

    public LocationVO(Double longitude, Double latitude, Double elevation) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }
}

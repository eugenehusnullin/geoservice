package service.geo.dto;

public class ErrorObject {
    String status;
    String desc;

    public ErrorObject() {
        this.status = "error";
    }

    public ErrorObject(String desc) {
        this.status = "error";
        this.desc = desc;
    }
}

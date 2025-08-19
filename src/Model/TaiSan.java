package Model;

import java.io.Serializable;

public class TaiSan implements Serializable{
    private String maTaiSan;
    private String tenTaiSan;
    private String loaiTaiSan;
    private String viTriPhong;
    private double giaTri;

    public TaiSan() {
    }

    public TaiSan(String maTaiSan, String tenTaiSan, String loaiTaiSan, String viTriPhong, double giaTri) {
        this.maTaiSan = maTaiSan;
        this.tenTaiSan = tenTaiSan;
        this.loaiTaiSan = loaiTaiSan;
        this.viTriPhong = viTriPhong;
        this.giaTri = giaTri;
    }

    public String getMaTaiSan() {
        return maTaiSan;
    }

    public String getTenTaiSan() {
        return tenTaiSan;
    }

    public String getLoaiTaiSan() {
        return loaiTaiSan;
    }

    public String getViTriPhong() {
        return viTriPhong;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setMaTaiSan(String maTaiSan) {
        this.maTaiSan = maTaiSan;
    }

    public void setTenTaiSan(String tenTaiSan) {
        this.tenTaiSan = tenTaiSan;
    }

    public void setLoaiTaiSan(String loaiTaiSan) {
        this.loaiTaiSan = loaiTaiSan;
    }

    public void setViTriPhong(String viTriPhong) {
        this.viTriPhong = viTriPhong;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
    
}

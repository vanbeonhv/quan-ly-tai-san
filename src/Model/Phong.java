package Model;

import java.io.Serializable;

/**
 *
 * @author Marc
 */
public class Phong implements Serializable {
    
    private int maPhong;
    private String ten;
    private String moTa;
    
    public Phong() {
    }
    
    public Phong(int maPhong, String ten, String moTa) {
        this.maPhong = maPhong;
        this.ten = ten;
        this.moTa = moTa;
    }
    
    public Phong(String ten, String moTa) {
        this.ten = ten;
        this.moTa = moTa;
    }
    
    public int getMaPhong() {
        return maPhong;
    }
    
    public void setMaPhong(int maPhong) {
        this.maPhong = maPhong;
    }
    
    public String getTen() {
        return ten;
    }
    
    public void setTen(String ten) {
        this.ten = ten;
    }
    
    public String getMoTa() {
        return moTa;
    }
    
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
    
}
package Model;

import java.io.Serializable;

/**
 *
 * @author Marc
 */
public class Event implements Serializable {

    private String action;
    private TaiSan taisan;
    private Phong phong;

    public Event(String action, TaiSan taisan) {
        this.action = action;
        this.taisan = taisan;
        this.phong = null;
    }

    public Event(String action, Phong phong) {
        this.action = action;
        this.phong = phong;
        this.taisan = null;
    }

    public Event(String action) {
        this.action = action;
        this.taisan = null;
        this.phong = null;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public TaiSan getTaisan() {
        return taisan;
    }

    public void setTaisan(TaiSan taisan) {
        this.taisan = taisan;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

}

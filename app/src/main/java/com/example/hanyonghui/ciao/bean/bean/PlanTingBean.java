package com.example.hanyonghui.ciao.bean.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hanyonghui on 2017/8/17.
 */

public class PlanTingBean {

    @SerializedName("Firmware Rev")
    private String _$FirmwareRev194; // FIXME check this code
    private String IP;
    private String MAC;
    @SerializedName("MICO OS Rev")
    private String _$MICOOSRev185; // FIXME check this code
    private String Manufacturer;
    private String Model;
    private String Name;
    private int Port;
    private String Protocol;
    private String Seed;

    public String get_$FirmwareRev194() {
        return _$FirmwareRev194;
    }

    public void set_$FirmwareRev194(String _$FirmwareRev194) {
        this._$FirmwareRev194 = _$FirmwareRev194;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String get_$MICOOSRev185() {
        return _$MICOOSRev185;
    }

    public void set_$MICOOSRev185(String _$MICOOSRev185) {
        this._$MICOOSRev185 = _$MICOOSRev185;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String Manufacturer) {
        this.Manufacturer = Manufacturer;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getPort() {
        return Port;
    }

    public void setPort(int Port) {
        this.Port = Port;
    }

    public String getProtocol() {
        return Protocol;
    }

    public void setProtocol(String Protocol) {
        this.Protocol = Protocol;
    }

    public String getSeed() {
        return Seed;
    }

    public void setSeed(String Seed) {
        this.Seed = Seed;
    }
}

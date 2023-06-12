package com.namth.assignment;

import java.io.Serializable;

public class NhanVienModel implements Serializable {
    public String maNV;
    public String hoTen;
    public String phongBan;
    public NhanVienModel(String maNV, String hoTen, String phongBan){
        this.hoTen=hoTen;
        this.maNV=maNV;
        this.phongBan=phongBan;
    }
}

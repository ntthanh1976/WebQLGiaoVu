/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class MonHoc {

    private String mamh;
    private String tenmh;
    private int sotinchi;

    public MonHoc() {
    }

    public MonHoc(String mamh, String tenmh, int sotinchi) {
        this.mamh = mamh;
        this.tenmh = tenmh;
        this.sotinchi = sotinchi;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getTenmh() {
        return tenmh;
    }

    public void setTenmh(String tenmh) {
        this.tenmh = tenmh;
    }

    public int getSotinchi() {
        return sotinchi;
    }

    public void setSotinchi(int sotinchi) {
        this.sotinchi = sotinchi;
    }
}
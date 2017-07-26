/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

/**
 *
 * @author Qnaerhi
 */
public class Automaatti {
    public int id;
    public int cityID;
    public String city;
    public String address;
    public String name;
    public float lat;
    public float lng;
    public String openinghrs;
    
    Automaatti(int i, String a, String b, String c, float d, float f, String g) {
        cityID = i;
        city = a;
        address = b;
        name = c;
        lat = d;
        lng = f;
        openinghrs = g;
    }
            
}

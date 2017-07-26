/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.util.ArrayList;

/**
 *
 * @author Qnaerhi
 */
public class paketti {
    int pakettiID;
    int userID;
    int lk;
    int op;

    public int getPakettiID() {
        return pakettiID;
    }

    public void setPakettiID(int varastoID) {
        this.pakettiID = varastoID;
    }
    
    

    public paketti(int k, int l, int o) {
        userID = k;
        lk = l;
        op = o;
    }
    
    
}

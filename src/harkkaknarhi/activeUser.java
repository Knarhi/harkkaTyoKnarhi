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
public class activeUser {
    int id;
    String name;
    
    activeUser(int i, String j) {
        id = i;
        name = j;   
    }
    
    public String getName() {
            return name;
    }
    
    public int getId() {
        return id;
    }
}

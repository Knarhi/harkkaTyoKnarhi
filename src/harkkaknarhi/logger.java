/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.io.IOException;
import java.time.LocalDateTime;


/**
 *
 * @author Qnaerhi
 */
public class logger {
    public void log() throws IOException{
        IOfilehandler io = new IOfilehandler("log.txt");
        dataBase db = new dataBase();
        activeUser au = db.getActiveUser();
        String a = au.name;
        int i = au.getId();
        String fl = db.getLog(i);
        
        
        String end = a + ":n lokit "+ LocalDateTime.now() + " " + fl;
        io.writeText(end);
        db.insertLog(i,end);
    }
}

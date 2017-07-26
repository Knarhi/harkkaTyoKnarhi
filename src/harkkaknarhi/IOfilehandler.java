/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 *
 * @author Qnaerhi
 */
public class IOfilehandler {
    private String filename;
    
    public IOfilehandler(String s) {
        filename = s;
    }
    
    public void writeText(String text) throws FileNotFoundException, IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter(filename));
        out.write(text);
        out.close();
    
    }
    public String readText() throws FileNotFoundException, IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        StringBuilder allthelines = new StringBuilder();
        String line;
        while( (line = in.readLine()) != null) {
            allthelines.append(line);
        }
        return allthelines.toString();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;

/**
 *
 * @author Dimitrije
 */
public class OsveziNit extends Thread {
    
    private FormaServer fs ;

   

    public OsveziNit(FormaServer fs) {
        this.fs = fs;
    }

    
    
    @Override
    public void run() {
        
        while(!isInterrupted()){
            try {
                Thread.sleep(500);
                fs.popuniTabelu();
            } catch (InterruptedException ex) {
                System.getLogger(OsveziNit.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }
    
   
    
    
    
    
}

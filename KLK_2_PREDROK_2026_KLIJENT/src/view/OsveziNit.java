/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Dimitrije
 */
public class OsveziNit extends Thread {
    
    private LoginForma lf;

    public OsveziNit(LoginForma lf) {
        this.lf = lf;
    }

    @Override
    public void run() {
        
        for (int i = 10; i > 0; i--) {
            try {
                lf.popuniTimer(i+"s");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        
        lf.zatvoriLogin();
    }
    
    
    
    
    
    
    
}

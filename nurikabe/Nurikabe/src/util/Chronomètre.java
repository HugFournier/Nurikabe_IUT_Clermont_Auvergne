/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package util;

/**
 *
 * @author fourn
 */
public class Chronomètre {
    
    private long tmpDebut=0, tmpFin=0, total=0;
    private boolean actif=false;

    public Chronomètre() {
        this(0);
    }
    
    public Chronomètre(long total) {
        this.total=total;
    }
    
    public void start()
    {
        tmpDebut=System.currentTimeMillis();
        tmpFin=0;
        total=0;
        actif = true;
    }
    
    public void pause()
    {
        if(!actif) return;
        tmpFin=System.currentTimeMillis();
        total+=tmpFin-tmpDebut;
        actif=false;
    }
    
    public void resume()
    {
        if(actif) return;
        tmpDebut=System.currentTimeMillis();
        tmpFin=0;
        actif = true;
    }
    
    public void stop()
    {
        if(!actif) return;
        tmpFin=System.currentTimeMillis();
        total+=tmpFin-tmpDebut;
        tmpDebut=0;
        tmpFin=0;
        actif = false;
    }
    
    public long getDureeSec()
    {
        return getDureeMs()/1000;
    }
    
    public long getDureeMs()
    {
        return total + (actif ? System.currentTimeMillis()-tmpDebut : 0);
    }
    
    public String getDureeTxt()
    {
        return timeToHMS(getDureeSec());
    }
    
    public static String timeToHMS(long tempsS) {
        
        int h = (int) (tempsS / 3600);
        int m = (int) ((tempsS % 3600) / 60);
        int s = (int) (tempsS % 60);
        
        String r="";
        
        if(h>0) {
            r+=h+" h ";
        }
        if(m>0) {
            r+=m+" min ";
        }
        if(s>0) {
            r+=s+" s";
        }
        if(h<=0 && m<=0 && s<=0) {
            r="0 s";
        }
        
        return r;
    }

    @Override
    public String toString() {
        return getDureeMs()+"";
    }
}

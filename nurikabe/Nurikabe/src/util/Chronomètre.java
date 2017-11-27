/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package util;

import java.io.Serializable;

/**
 *
 * @author fourn
 */
public class Chronomètre implements Serializable{
    
    private static final long serialVersionUID = -7060644478953544967L;
    
    private long tmpDebut=0, total=0;
    private boolean actif=false;

    public Chronomètre() {
        this(0);
    }
    
    public Chronomètre(long total) {
        this.total=total;
    }
    
    public void lancer()
    {
        if(actif) return;
        tmpDebut=System.currentTimeMillis();
        actif = true;
    }
    
    public void pause()
    {
        if(!actif) return;
        total+=System.currentTimeMillis()-tmpDebut;
        actif = false;
    }
    
    public void reset()
    {
        total = 0;
        actif = false;
    }
    
    public void reset(boolean relancer)
    {
        reset();
        if(relancer)
            lancer();
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

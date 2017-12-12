/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.logic.palmares;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author fourn
 */
public class Palmares implements Serializable, Comparable {
    private String id;
    long temps;
    int taille;

    public Palmares(String id, int taille, long temps) {
        this.id = id;
        this.taille = taille;
        this.temps = temps;
    }

    public String getId() {
        return id;
    }

    public int getTaille() {
        return taille;
    }

    public long getTemps() {
        return temps;
    }

    @Override
    public String toString() {
        return "Palmares{" + "id=" + id + ", taille=" + taille + ", temps=" + temps + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Palmares other = (Palmares) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        if(equals(0)){
            return (int) (((Palmares) o).getTemps() - temps);
        }
        return 0;
    }
    
    
}

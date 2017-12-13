/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.jeu.logic.palmares;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author fourn
 */
public class Palmares implements Comparable {

    private SimpleStringProperty id;
    private SimpleLongProperty temps;
    private SimpleIntegerProperty taille;

    public Palmares(String id, int taille, long temps) {
        this.id = new SimpleStringProperty(id);
        this.temps = new SimpleLongProperty(temps);
        this.taille = new SimpleIntegerProperty(taille);
    }

    public String getId() {
        return id.get();
    }

    public int getTaille() {
        return taille.get();
    }

    public long getTemps() {
        return temps.get();
    }

    @Override
    public String toString() {
        return getId() + System.lineSeparator() + getTaille() + " " + getTemps();
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
        if (!Objects.equals(this.getId(), other.getId())) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        if (equals(0)) {
            return (int) (((Palmares) o).getTemps() - temps.get());
        }
        return 0;
    }
}

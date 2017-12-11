/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nurikabe.affichage.events;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.Node;

/**
 *
 * @author sylat
 */
public class caseClickedEvent extends Event{
    public static final EventType<caseClickedEvent> CASE_CLICKED_AVEC_SENDER =
                new EventType<caseClickedEvent>(Event.ANY, "CASE_CLICKED_AVEC_SENDER");
    public static final EventType<caseClickedEvent> CASE_CLICKED_AVEC_POSITION =
                new EventType<caseClickedEvent>(Event.ANY, "CASE_CLICKED_AVEC_POSITION");
    
    private Node sender;
    private Integer colone;
    private Integer ligne;
    public Integer getColone() {
        return colone;
    }
    public Integer getLigne() {
        return ligne;
    }
    public Node getSender() {
        return sender;
    }
    
    public boolean isSenderType(){
        return getEventType() == CASE_CLICKED_AVEC_SENDER;
    }
    
    public caseClickedEvent(Node n) {
        super(CASE_CLICKED_AVEC_SENDER);
        sender = n;
    }
    
    public caseClickedEvent(int x, int y){
        super(CASE_CLICKED_AVEC_POSITION);
        ligne = y;
        colone = x;
    }
}

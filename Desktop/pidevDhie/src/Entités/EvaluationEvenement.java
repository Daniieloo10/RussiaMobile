/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author boussandel
 */
public class EvaluationEvenement {
    private int evaluationId;
    private int noteEvenement;
    private String prenom_Participant;
    private String nomParticipant;
    
    public EvaluationEvenement() {
        
    }
    public EvaluationEvenement(int ev,int note,String prenom , String nom) {
        this.evaluationId=ev ; 
        this.noteEvenement=note ; 
        this.prenom_Participant=prenom ; 
        this.nomParticipant=nom ;
        
    }

    public int getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(int evaluationId) {
        this.evaluationId = evaluationId;
    }

    public int getNoteEvenement() {
        return noteEvenement;
    }

    public void setNoteEvenement(int noteEvenement) {
        this.noteEvenement = noteEvenement;
    }

    public String getPrenom_Participant() {
        return prenom_Participant;
    }

    public void setPrenom_Participant(String prenom_Participant) {
        this.prenom_Participant = prenom_Participant;
    }

    public String getNomParticipant() {
        return nomParticipant;
    }

    public void setNomParticipant(String nomParticipant) {
        this.nomParticipant = nomParticipant;
    }
    
    
    
    
    
    
    
}

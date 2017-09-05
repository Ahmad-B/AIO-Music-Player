/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radio.logic.objects;

/**
 *
 * @author Priyan
 */
public class Artist{
    public int ID;
    public String Name;
    public Artist(){}
    public Artist(int ID, String Name){
        this.ID=ID;
        this.Name=Name;
    }
    public boolean equals(Artist a){
        return a.ID == ID;
    }
    public String getName()
     {
         return Name;
     }
}

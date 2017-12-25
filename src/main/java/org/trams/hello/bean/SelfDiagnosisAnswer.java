/*
 * Created on 23 Mar 2017 ( Time 10:21:04 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.trams.hello.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
@DynamicInsert
@DynamicUpdate
public class SelfDiagnosisAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    private Integer point;

    @NotNull
    @Size( min = 1, max = 65535 )
    private String content;

    @NotNull
    private Integer selfDiagnosisField;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setPoint( Integer point ) {
        this.point = point;
    }
    public Integer getPoint() {
        return this.point;
    }

    public void setContent( String content ) {
        this.content = content;
    }
    public String getContent() {
        return this.content;
    }

    public void setSelfDiagnosisField( Integer selfDiagnosisField ) {
        this.selfDiagnosisField = selfDiagnosisField;
    }
    public Integer getSelfDiagnosisField() {
        return this.selfDiagnosisField;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(point);
        // attribute 'content' not usable (type = String Long Text)
        sb.append("|");
        sb.append(selfDiagnosisField);
        return sb.toString(); 
    } 


}
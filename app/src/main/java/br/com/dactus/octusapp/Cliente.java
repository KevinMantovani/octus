package br.com.dactus.octusapp;

import java.io.Serializable;

public class Cliente implements Serializable {

    private Integer id;
    private String nome;

    //METÓDO GETTERS E SETTERS DOS ATRIBUTOS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    private String cpfCnpj;


    @Override
    public String toString(){
        return nome;
    }
}

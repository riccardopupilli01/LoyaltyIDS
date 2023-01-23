package it.unicam.cs.ids.loyaltyIDS.Model;



import java.util.Objects;


public class UtenteGenerico {
    private int id;
    private String Nome;

    private String cognome;
    private String indirizzo;

    private String email;

    private Number telefono;

    private String username;
    private String password;



    public UtenteGenerico(String nome, String cognome,  String indirizzo, String email, Number telefono,  String username, String password) {
        this.id=randomInt();
        this.Nome = nome;
        this.cognome=cognome;
        this.indirizzo = indirizzo;
        this.email=email;
        this.telefono=telefono;
        this.username=username;
        this.password=password;
    }

    private int randomInt() {
        double doubleRandom=0;

            doubleRandom=Math.random()*4000;

        int intRandom=(int ) doubleRandom;
        return intRandom;
    }


    public int getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public Number getTelefono() {
        return telefono;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UtenteGenerico that = (UtenteGenerico) o;
        return Objects.equals(id, that.id) && Objects.equals(email, that.email) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username);
    }
}



package exemplos.android.sqlite;

public class Contato {

  int _id;
  String nome;
  String telefone;

  public Contato() {

  }

  public Contato(int id, String nome, String telefone) {
    this._id = id;
    this.nome = nome;
    this.telefone = telefone;
  }

  //gets and setters

  public Contato(String nome, String telefone) {
    this.nome = nome;
    this.telefone = telefone;
  }

  public int getID() {
    return this._id;
  }

  public void setID(int id) {
    this._id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }
}
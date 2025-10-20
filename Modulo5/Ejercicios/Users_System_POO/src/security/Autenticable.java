package security;

public interface Autenticable {

    boolean authenticate(String email, String password);

    void setPassword(String password);
}

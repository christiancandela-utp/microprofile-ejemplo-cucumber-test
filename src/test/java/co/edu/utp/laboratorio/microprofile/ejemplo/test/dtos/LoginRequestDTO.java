package co.edu.utp.laboratorio.microprofile.ejemplo.test.dtos;

public class LoginRequestDTO {
    private final String username;
    private final String password;

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static LoginRequestDTO of(String username, String password){
        return new LoginRequestDTO(username, password);
    }
}

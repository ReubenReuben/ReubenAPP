package webApp.ReubenApp.Dto;

public class AuthenticationDto {
    private  String name;
    private  String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationDto() {
    }

    public AuthenticationDto(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthenticationDto{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

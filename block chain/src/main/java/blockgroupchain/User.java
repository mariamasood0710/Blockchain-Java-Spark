package blockgroupchain;

public class User extends Block {
    private String username;
    private String passwordHash;

    public User(int index, String username, String password, String previousHash, int proof) {
        super(index, "", previousHash, proof);
        this.username = username;
        this.passwordHash = encryptPassword(password);
        this.setData("User: " + username);
        this.setHash(calculateHash());
    }

    private String encryptPassword(String rawPassword) {
        return StringUtil.applySha256(rawPassword);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}

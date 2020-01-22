package mate.academy.internetshop.model;

public class Role {
    private static long ID = 0;
    private RoleName roleName;
    private Long id;

    public enum RoleName {
        USER, ADMIN;
    }

    public Role(RoleName roleName) {
        this.id = ++ID;
        this.roleName = roleName;
    }

    public static Role of(String roleName) {
        return new Role(RoleName.valueOf(roleName));
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

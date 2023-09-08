package tr.com.bookcell.user.admin;

public class DefaultAdminService implements AdminService {
    private final AdminRepository adminRepository;

    public DefaultAdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void add(String userName, String password) {
        Admin admin = new Admin(userName, password);
        adminRepository.add(admin);
    }


}

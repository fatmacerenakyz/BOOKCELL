package tr.com.bookcell.user.admin;

public class DefaultAdminService implements AdminService {
    private final AdminRepository adminRepository;

    public DefaultAdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void add(String password, String userName) {
        Admin admin = new Admin(password, userName);
        adminRepository.add(admin);
    }

    @Override
    public Admin get() {
        return adminRepository.get();
    }


}

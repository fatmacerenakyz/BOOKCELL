package tr.com.bookcell.user.admin;

import tr.com.bookcell.BaseRepository;

public interface AdminRepository extends BaseRepository {
    void add(Admin admin);
    Admin get();
}

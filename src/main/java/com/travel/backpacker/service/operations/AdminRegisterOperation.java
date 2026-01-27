package com.travel.backpacker.service.operations;

import com.travel.backpacker.dto.UserWrapper;
import com.travel.backpacker.dto.iuser.AdminUser;
import com.travel.backpacker.dto.ruser.RAddress;
import com.travel.backpacker.dto.ruser.RAdmin;
import com.travel.backpacker.model.Address;
import com.travel.backpacker.model.Admin;
import com.travel.backpacker.repository.AdminDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Locale;

public class AdminRegisterOperation extends AbstractAccessOperation implements Operation<AdminUser> {
    private final RAdmin rAdmin;
    private final AdminDao adminDao = requiredComponents.getAdminDao();
    private final String plaform;

    private final SCryptPasswordEncoder passwordEncorder = requiredComponents.getPasswordEncorder();

    public AdminRegisterOperation(UserWrapper userWrapper, OperationRequiredComponents requiredComponents, RAdmin rAdmin, String plaform) {
        super(userWrapper, requiredComponents);
        this.rAdmin = rAdmin;
        this.plaform = plaform;

    }

    @Override
    public ResponseEntity execute(AdminUser adminUser, Object... params) {
        Admin admin = adminDao.save(createAdmin(adminUser));
        return new ResponseEntity<>(admin, HttpStatus.CREATED);

    }

    private Admin createAdmin(AdminUser adminUser) {
        Admin admin = new Admin();
        admin.setId(adminUser.getId());
//		admin.setFirstName( rAdmin.getFirstname() );
//		admin.setLastName( rAdmin.getLastname() );
        admin.setUsername(rAdmin.getUsername().toLowerCase(Locale.ENGLISH));
        CharSequence seq = java.nio.CharBuffer.wrap(rAdmin.getPassword());
        char[] encodedPassword = passwordEncorder.encode(seq).toCharArray();
        admin.setPassword(encodedPassword);
        admin.setEmail(rAdmin.getEmail());
//		admin.setPhone( rAdmin.getPhone() );
//		admin.setPlatform( rAdmin.getPlatform() );
//		admin.setAddress( createAddress( rAdmin.getrAddress() ) );
        return admin;
    }

    private Address createAddress(RAddress rAddress) {
        Address address = new Address();
        address.setCity(rAddress.getCity());
        address.setCounty(rAddress.getCounty());
        address.setLine1(rAddress.getLine1());
        address.setLine2(rAddress.getLine2());
        address.setPostcode(rAddress.getPostcode());
        return address;
    }
}

package services;

import dataaccess.UserDB;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

public class AccountService {

    public User login(String email, String password) {
        UserDB userDB = new UserDB();

        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }

        return null;
    }

    public void resetPassword(String email, String path, String url) {

        try {
            UserDB userDB = new UserDB();
            User user = userDB.get(email);
            String uuid = UUID.randomUUID().toString();
            user.setResetPasswordUuid(uuid);
            userDB.update(user);
            HashMap<String, String> hashmap = new HashMap<>();
            hashmap.put("firstName", user.getFirstName());
            hashmap.put("lastName", user.getLastName());
            hashmap.put("link", url + "?uuid=" + uuid);
            GmailService.sendMail(email, "Password Reset", path + "/emailtemplates/resetpassword.html", hashmap);
        } catch (Exception e) {
            Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "No email Exists", email);
        }
    }

    public boolean changePassword(String uuid, String password) {
        System.out.println("IN CHANGE PASSWORD");        
        try {
            System.out.println("IN TRY BLOCK");
            UserDB userDB = new UserDB();
            User user = userDB.getByUUID(uuid);
            System.out.println(user.toString());
            user.setPassword(password);
            user.setResetPasswordUuid(null);
            userDB.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

}

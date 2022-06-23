package utebayev.dias.finalprojectjavaadvance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import utebayev.dias.finalprojectjavaadvance.entities.User;
import utebayev.dias.finalprojectjavaadvance.repositories.UserRepository;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> showAll() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    public void makeUserEdit(long id) {
        User user = userRepository.findById(id).get();
        user.getRoles().iterator().next().setName("EDITOR");
        userRepository.save(user);
    }

    public void makeUserUser(long id) {
        User user = userRepository.findById(id).get();
        user.getRoles().iterator().next().setName("USER");
        userRepository.save(user);
    }

}

package utebayev.dias.finalprojectjavaadvance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utebayev.dias.finalprojectjavaadvance.entities.Cartoon;
import utebayev.dias.finalprojectjavaadvance.repositories.CartoonRepository;

@Service
public class CartoonService {

    @Autowired
    private CartoonRepository cartoonRepository;

    public Iterable<Cartoon> showCartoons() {
        Iterable<Cartoon> cartoons = cartoonRepository.findAll();
        return cartoons;
    }

    public Cartoon showEditCartoonForm(Long id) {
        Cartoon cartoon = cartoonRepository.findById(id).get();
        return cartoon;
    }

    public void deleteCartoon(Long id) {
        cartoonRepository.deleteById(id);
    }

    public boolean addNewCartoon(Cartoon cartoon) {
        if(cartoonRepository.getCartoonByName(cartoon.getName()) != null) {
            return false;
        }
        cartoon.setGenreId(1);
        cartoonRepository.save(cartoon);
        return true;
    }



}

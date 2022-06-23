package utebayev.dias.finalprojectjavaadvance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utebayev.dias.finalprojectjavaadvance.entities.Serial;
import utebayev.dias.finalprojectjavaadvance.repositories.SerialRepository;

@Service
public class SerialService {
    @Autowired
    private SerialRepository serialRepository;



    public Iterable<Serial> showSerials() {
        Iterable<Serial> serials = serialRepository.findAll();
        return serials;
    }

    public Serial showEditSerialForm(Long id) {
        Serial serial = serialRepository.findById(id).get();
        return serial;
    }
    public void deleteSerial(Long id) {
        serialRepository.deleteById(id);
    }

    public boolean addNewSerial(Serial serial) {
        if(serialRepository.getSerialByName(serial.getName()) != null) {
            return false;
        }
        serial.setGenreId(1);
        serialRepository.save(serial);
        return true;
    }
}

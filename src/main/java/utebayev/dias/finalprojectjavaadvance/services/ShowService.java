package utebayev.dias.finalprojectjavaadvance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utebayev.dias.finalprojectjavaadvance.entities.TVShow;
import utebayev.dias.finalprojectjavaadvance.repositories.TVShowRepository;

@Service
public class ShowService {

    @Autowired
    private TVShowRepository showRepository;

    public Iterable<TVShow> showShows() {
        Iterable<TVShow> tvShows = showRepository.findAll();
        return tvShows;
    }

    public TVShow showEditShowForm(Long id) {
        TVShow tvShows = showRepository.findById(id).get();
        return tvShows;
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }

    public boolean addNewShow(TVShow tvShows) {
        if(showRepository.getShowByName(tvShows.getName()) != null) {
            return false;
        }
        tvShows.setGenreId(1);
        showRepository.save(tvShows);
        return true;
    }
}

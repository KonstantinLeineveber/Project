package com.tms.springapp.service.impl;

import com.tms.springapp.model.film.Film;
import com.tms.springapp.repository.film.FilmRepository;
import com.tms.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("filmService")
@Transactional
public class FilmService implements IService<Film> {
    private final FilmRepository filmRepository;
    private final Logger logger = LoggerFactory.getLogger(FilmService.class);

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Film findById(long id) {
        Optional<Film> byId = filmRepository.findById(id);
        if (byId.isPresent()) {
            Film film = byId.get();
            logger.info("Film {} was successfully found", film.getId());
            return film;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Film> findAllWithPagination(Pageable pageable) {
        Page<Film> films = filmRepository.findAll(pageable);
        logger.info("All films in db was successfully found");
        return films;
    }


    @Override
    public Film save(Film film) {
        Film filmToDb = filmRepository.save(film);
        logger.info("Film {} was successfully added to db", filmToDb.getId());
        return filmToDb;
    }

    @Override
    public void deleteById(long id) {
        filmRepository.deleteById(id);
        logger.info("Film with id {} was successfully deleted form db", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Film> findAll() {
        List<Film> films = filmRepository.findAll();
        logger.info("All films was successfully found in db");
        return films;
    }
}

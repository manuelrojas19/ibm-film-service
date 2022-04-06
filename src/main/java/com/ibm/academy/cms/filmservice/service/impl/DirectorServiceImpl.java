package com.ibm.academy.cms.filmservice.service.impl;

import com.ibm.academy.cms.filmservice.entity.Director;
import com.ibm.academy.cms.filmservice.repository.DirectorRepository;
import com.ibm.academy.cms.filmservice.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorServiceImpl extends GenericServiceImpl<Director, DirectorRepository> implements DirectorService {

    @Autowired
    public DirectorServiceImpl(DirectorRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Director update(Long id, Director director) {
        Director directorToUpdate = this.findById(id);
        directorToUpdate.setName(director.getName());
        directorToUpdate.setDescription(director.getDescription());
        directorToUpdate.setBornDate(director.getBornDate());
        directorToUpdate.setBornPlace(director.getBornPlace());
        return repository.save(directorToUpdate);
    }
}

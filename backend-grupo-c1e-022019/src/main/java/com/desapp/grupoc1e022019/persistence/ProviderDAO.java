package com.desapp.grupoc1e022019.persistence;

import com.desapp.grupoc1e022019.model.Provider;
import com.desapp.grupoc1e022019.services.dtos.ProviderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProviderDAO {

    @Autowired
    private ProviderRepository providerRepository;

    public void save(Provider provider){
        providerRepository.save(provider);
    }

    public void delete(Long id) {
        providerRepository.deleteById(id);
    }

    public boolean providerExists(long idProvider) {
            return providerRepository.findById(idProvider).isPresent();
    }

    public Provider getProvider(long id) {
            return providerRepository.getOne(id);
    }

    public boolean existsProviderWithSameName(String name) {
        return !providerRepository.findByName(name).isEmpty();
    }
}
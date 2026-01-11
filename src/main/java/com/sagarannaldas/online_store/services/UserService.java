package com.sagarannaldas.online_store.services;

import com.sagarannaldas.online_store.entities.Address;
import com.sagarannaldas.online_store.entities.User;
import com.sagarannaldas.online_store.repositories.AddressRepository;
import com.sagarannaldas.online_store.repositories.ProfileRepository;
import com.sagarannaldas.online_store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;

    @Transactional
    public void showEntityStates() {
        var user = User.builder()
                .name("John")
                .email("john@gmail.com")
                .password("password")
                .build();

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/ Detached");
        }
        userRepository.save(user);

        if (entityManager.contains(user)) {
            System.out.println("Persistent");
        } else {
            System.out.println("Transient/ Detached");
        }
    }

    @Transactional
    public void showRelatedEntityStates() {
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());
    }

    public void fetchAddress(Long userId) {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    public void persistRelated() {
        var user = User.builder()
                .name("mac")
                .email("mac@gmail.com")
                .password("password")
                .build();

        var address = Address.builder()
                .city("hyd")
                .state("hyd")
                .street("kphb")
                .zip("50003")
                .build();

        user.addAddress(address);
        userRepository.save(user);
    }
}

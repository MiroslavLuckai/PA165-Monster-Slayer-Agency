package cz.muni.fi.pa165.msa.service.facade;

import cz.muni.fi.pa165.monsterslayeragency.entities.User;
import cz.muni.fi.pa165.msa.dto.AuthenticationResponseDTO;
import cz.muni.fi.pa165.msa.dto.UserDTO;
import cz.muni.fi.pa165.msa.dto.UserRegistrationDTO;
import cz.muni.fi.pa165.msa.facade.UserFacade;
import cz.muni.fi.pa165.msa.service.BeanMappingService;
import cz.muni.fi.pa165.msa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class UserFacadeImpl implements UserFacade {

    @Autowired
    UserService service;

    @Autowired
    BeanMappingService mapper;

    @Override
    public UserDTO registerUser(UserRegistrationDTO registrationDTO) {
        User user = mapper.mapTo(registrationDTO.getUser(), User.class);
        return mapper.mapTo(service.registerUser(user, registrationDTO.getPassword()), UserDTO.class);
    }

    @Override
    public AuthenticationResponseDTO authenticateUser(String email, String password) {
        User user = service.findUserByEmail(email);
        AuthenticationResponseDTO response = new AuthenticationResponseDTO();
        response.setSuccess(service.authenticate(user, password));
        if (response.isSuccess()) {
            response.setUser(mapper.mapTo(user, UserDTO.class));
            return response;
        }
        response.setUser(null);
        return response;
    }

    @Override
    public void updateUser(UserDTO userDto) {
        User user = mapper.mapTo(userDto, User.class);
        service.updateUser(user);
    }

    @Override
    public void removeUser(Long userId) {
        User user = service.findUserById(userId);
        service.removeUser(user);
    }

    @Override
    public UserDTO findUserById(Long id) {
        User user = service.findUserById(id);
        return (user == null) ? null : mapper.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = service.findUserByEmail(email);
        return (user == null) ? null : mapper.mapTo(user, UserDTO.class);
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        User user = service.findUserByUsername(username);
        return (user == null) ? null : mapper.mapTo(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = service.findAll();
        return mapper.mapTo(users, UserDTO.class);
    }
}

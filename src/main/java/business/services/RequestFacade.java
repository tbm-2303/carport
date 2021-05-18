package business.services;

import business.entities.Requesty;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.RequestMapper;

import java.util.List;

public class RequestFacade {
    RequestMapper requestMapper;

    public RequestFacade(Database database) {
        requestMapper = new RequestMapper(database);

    }

    public int createRequest(Requesty request1) {
        return requestMapper.createRequest(request1);
    }

    public List<Requesty> getAllRequest() throws UserException {
       return requestMapper.getAllRequest();
    }
}

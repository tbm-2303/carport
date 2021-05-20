package business.services;

import business.entities.Request_obj;
import business.entities.Request;
import business.exceptions.UserException;
import business.persistence.Database;
import business.persistence.RequestMapper;

import java.util.List;

public class RequestFacade {
    RequestMapper requestMapper;

    public RequestFacade(Database database) {
        requestMapper = new RequestMapper(database);

    }

    public Request_obj createRequest(Request_obj request1) throws UserException {
        return requestMapper.CreateRequest(request1);
    }

    public List<Request> getAllRequest(String status) throws UserException {
       return requestMapper.getAllRequest(status);
    }
    public List<Request_obj> getAllRequest2(String status) throws UserException {
        return requestMapper.getAllRequest2(status);
    }

}

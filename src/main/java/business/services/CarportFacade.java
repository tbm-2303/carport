package business.services;

import business.entities.Carport;
import business.entities.User;
import business.exceptions.UserException;
import business.persistence.CarportMapper;
import business.persistence.Database;
import business.persistence.UserMapper;

import java.util.List;

public class CarportFacade {
    CarportMapper carportMapper;

    public CarportFacade(Database database)
    {
        carportMapper = new CarportMapper(database);
    }

    public List<Carport> getAllCarports() throws UserException {
        return carportMapper.getAllCarports();
    }
    public void createCarport(Carport carport) throws UserException {
        carportMapper.CreateCarport(carport);
    }
    public void updateCarportInfo(String info){

    }
    public void updateCarportDimensions(int width, int length, int shed_length, int shed_width){

    }
    public void updateCarportPrice(double price) {

    }
    public void deleteCarport(int carport_id){

    }
}

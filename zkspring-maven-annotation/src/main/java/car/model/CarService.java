package car.model;

import java.util.List;

public class CarService {
	
	CarDAO_Interface dao = new CarDAO();
	CarVO carvo = new CarVO();
	List<CarVO> carlist = dao.retrieveAllCar();
	
	public void insert(CarVO carvo) {
		dao.insert(carvo);
	}
	
	public void update(CarVO carvo) {
		dao.update(carvo);
	}

	public List<CarVO> retrieveAllCar(){
		return dao.retrieveAllCar();
	}

	public CarVO retrieveCarById(Integer id){
		return dao.retrieveCarById(id);
	}	
}

package car.model;

import java.util.List;

public interface CarDAO_Interface {
	public void insert(CarVO carvo);
	public void update(CarVO carvo);
	public List<CarVO> retrieveAllCar();
	public CarVO retrieveCarById(Integer id);
}

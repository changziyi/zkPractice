package car.vm;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import car.model.CarService;
import car.model.CarVO;

public class CarVm extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	private CarVO carvo = new CarVO();
	private CarService service = new CarService();
	private ListModel<CarVO> cars;

	@Wire // 設定wire後，selectorcomponent會尋找zk的view內的carlist這個component，並將變數指定給他。
	private Window win;

	public CarVm() {
		this.cars = new ListModelList<CarVO>(service.retrieveAllCar());
		((ListModelList<CarVO>) cars).setMultiple(true);
	}

	public ListModel<CarVO> getCars() {
		return cars;
	}

	public void setCars(ListModel<CarVO> cars) {
		this.cars = cars;
	}

	public Window getWin() {
		return win;
	}

	public void setWin(Window win) {
		this.win = win;
	}

	public CarVO getCarvo() {
		return carvo;
	}

	public void setCarvo(CarVO carvo) {
		this.carvo = carvo;
	}

	@Command
	@NotifyChange("carvo")
	public void retrieveCarById() {
		this.carvo = service.retrieveCarById(carvo.getCnumber());
	}

	@Command
	public void insert() {
		service.insert(carvo);
	}

	@Command
	public void retrieveAllCar() {
		// this.cars=service.retrieveAllCar();
		this.cars = new ListModelList<CarVO>(service.retrieveAllCar());
		((ListModelList<CarVO>) cars).setMultiple(true);

	}
}

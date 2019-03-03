package com.hospital;

public class DepartmentDispatcher {
	CardiologyView cardio = new CardiologyView();
	ENTView ent = new ENTView();
	OrthopedicView ortho = new OrthopedicView();

	public boolean DispatcherRequest(String service) {
		if (service.equalsIgnoreCase("CARDIOLOGY")) {
			cardio.view();
			return true;

		} else if (service.equalsIgnoreCase("ENT")) {
			ent.view();
			return true;
		} else if (service.equalsIgnoreCase("ORTHOPEDIC")) {
			ortho.view();
			return true;
		} else {
			return false;
		}
	}

}
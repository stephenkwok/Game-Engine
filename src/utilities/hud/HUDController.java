package Utilities.HUD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class HUDController implements Observer {

	private HUDModel model;
	private AbstractHUDScreen view;
	private Object dataSource;

	public void setDataSource(Object dataSource) {
		this.dataSource = dataSource;
	}

	public void init(String filename, Object dataSource) {
		setModel(new HUDModel());
		setDataSource(dataSource);
		IValueFinder valueFinder = new TLGCSValueFinder();
		valueFinder.setController(this);
		valueFinder.setDataSource(dataSource);

		List<String> fieldsToObserve = getFieldsToFollow(filename);
		Map<Integer, String> rowToValueMap = new HashMap<>();
		for (int i = 0; i < fieldsToObserve.size(); i++) {
			String field = fieldsToObserve.get(i);
			model.getData().put(field, valueFinder.find(field));
			rowToValueMap.put(i, field);
		}
		setView(new HUDScreen(model.getData(), rowToValueMap));
	}

	private List<String> getFieldsToFollow(String filename) {
		List<String> params = new ArrayList<>();
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
			String currentLine = bufferedReader.readLine();

			while (currentLine != null) {
				String trimmedWord = currentLine.trim();
				if (trimmedWord.length() != 0) {
					params.add(trimmedWord);
				}
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("A error occured reading file: " + e);
			e.printStackTrace();
		}
		return params;
	}

	public void setModel(HUDModel model) {
		this.model = model;
		this.model.addObserver(this);
	}

	public void setView(AbstractHUDScreen view) {
		this.view = view;
		this.view.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		ValueChange change = (ValueChange) arg;
		model.handleChange(change);
		view.handleChange(change);
	}

}

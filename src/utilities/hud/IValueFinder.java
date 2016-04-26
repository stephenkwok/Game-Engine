package utilities.hud;

public interface IValueFinder {

	public Property find(String key);

	public void setController(HUDController controller);

	public void setDataSource(Object dataSource);

}

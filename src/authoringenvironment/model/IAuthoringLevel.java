package authoringenvironment.model;

import java.util.List;

import gameengine.controller.IPlayLevel;
import javafx.scene.image.ImageView;

public interface IAuthoringLevel extends IEditableGameElement{

	public void PreviewUnitWithEditable(IAuthoringLevel level, IEditingEnvironment environment);
	
	public List<IPlayLevel> getLevels();
	
	public String getMyBackgroundImgName();
	
	public void setImageView(ImageView imageView);

	public void setPlayPosition(int size);
} 
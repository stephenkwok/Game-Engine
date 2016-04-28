package authoringenvironment.model;

import java.lang.reflect.*;
import java.util.*;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import gameengine.model.Actor;
import gameengine.model.ActorState;

/**
 * This class creates a list of Actors with attributes pre-defined in a resource
 * file
 * 
 * @author Stephen
 *
 */
public class PresetActorFactory {

	private static final String ACTORS_KEY = "Actors";
	private static final String METHOD_TYPES_KEY = "MethodTypes";
	private static final String DELIMITER = ",";
	private static final String METHODS = "Methods";
	private static final String METHOD = "Method";
	private static final String EXECUTE = "execute";
	@XStreamOmitField
	private ResourceBundle myResources;
	private List<Actor> myActors;
	private Map<String, List<String>> myMethodsMap;
	private List<String> actorNames;

	public PresetActorFactory(ResourceBundle resources) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		myResources = resources;
		myActors = new ArrayList<>();
		initializeMethodsMap();
		initializeActorNameList();
		createActors();
	}

	/**
	 * Initializes Map mapping method types (defined by what parameter(s) those
	 * methods take in as arguments) represented by Strings to a List of method
	 * names (names of methods that can be invoked on the Actor class) also
	 * represented by Strings
	 */
	private void initializeMethodsMap() {
		myMethodsMap = new HashMap<String, List<String>>();
		List<String> methodTypes = Arrays.asList(myResources.getString(METHOD_TYPES_KEY).split(DELIMITER));
		methodTypes.stream().forEach(type -> getMethodsForType(type));
	}

	/**
	 * Populates myMethodsMap with keys representing different types of
	 * parameters methods can take as arguments, and values representing a list
	 * of methods that fit that method type
	 * 
	 * @param type:
	 *            String representation of the type of parameter a method takes
	 *            as an argument
	 */
	private void getMethodsForType(String type) {
		myMethodsMap.put(type, Arrays.asList(myResources.getString(type).split(DELIMITER)));
	}

	/**
	 * Fills the list actorNames with the names of Actors to be created
	 */
	private void initializeActorNameList() {
		actorNames = Arrays.asList(myResources.getString(ACTORS_KEY).split(DELIMITER));
	}

	/**
	 * 
	 * Creates an Actor for each actor name in the actorNames list and sets that
	 * actor's attributes according to the specifications predefined in a
	 * properties file
	 * 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private void createActors() throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		for (String actorName : actorNames) {
			Actor actor = new Actor();
			myActors.add(actor);
			List<String> methods = Arrays.asList(myResources.getString(actorName + METHODS).split(DELIMITER));
			for (String methodName : methods) {
				Method factoryMethodToCall = getFactoryMethod(methodName);
				String parameter = myResources.getString(actorName + methodName);
				factoryMethodToCall.invoke(this, actor, methodName, parameter);
			}
		}
	}

	/**
	 * 
	 * @param methodName:
	 *            the name of the Actor method to be created and invoked
	 * @return a Method within the PresetActorFactory class that creates and
	 *         invokes the Actor method to be created
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private Method getFactoryMethod(String methodName) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (String methodType : myMethodsMap.keySet()) {
			if (myMethodsMap.get(methodType).contains(methodName)) {
				Method methodToCall = this.getClass().getDeclaredMethod(EXECUTE + methodType + METHOD, Actor.class,
						String.class, String.class);
				return methodToCall;
			}
		}
		return null;
	}

	/**
	 * Executes an Actor's method that takes in a String as a parameter
	 * 
	 * @param actor:
	 *            the Actor on which a method to be created is invoked
	 * @param method:
	 *            the method to be invoked on an Actor
	 * @param parameter:
	 *            the argument to be passed into the method created
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private void executeStringMethod(Actor actor, String method, String parameter) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method methodToCall = actor.getClass().getDeclaredMethod(method, String.class);
		methodToCall.invoke(actor, parameter.trim());
	}

	/**
	 * Executes an Actor's method that takes in an int as a parameter
	 * 
	 * @param actor:
	 *            the Actor on which a method to be created is invoked
	 * @param method:
	 *            the method to be invoked on an Actor
	 * @param parameter:
	 *            the argument to be passed into the method created (as String)
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private void executeIntMethod(Actor actor, String method, String parameter) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method methodToCall = actor.getClass().getDeclaredMethod(method, int.class);
		methodToCall.invoke(actor, Integer.parseInt(parameter.trim()));
	}

	/**
	 * Executes an Actor's method that takes in a boolean as a parameter
	 * 
	 * @param actor:
	 *            the Actor on which a method to be created is invoked
	 * @param method:
	 *            the method to be invoked on an Actor
	 * @param parameter:
	 *            the argument to be passed into the method created (as String)
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private void executeBooleanMethod(Actor actor, String method, String parameter) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method methodToCall = actor.getClass().getDeclaredMethod(method, boolean.class);
		methodToCall.invoke(actor, Boolean.parseBoolean(parameter.trim()));
	}

	/**
	 * Executes an Actor's method that takes in an Enum as a parameter
	 * 
	 * @param actor: the Actor on which a method to be created is invoked
	 * @param method: the method to be invoked on an Actor
	 * @param parameter: the argument to be passed into the method created (as String)
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("unused")
	private void executeEnumMethod(Actor actor, String method, String parameter) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method methodToCall = actor.getClass().getDeclaredMethod(method, ActorState.class);
		methodToCall.invoke(actor, ActorState.valueOf(parameter.trim()));
	}

	/**
	 * 
	 * @return: the List of Actors created by the PresetActorFactory
	 */
	public List<Actor> getPresetActors() {
		return myActors;
	}

}

package ALP.KBEWarehouse;

import java.util.List;

import ALP.RabbitMessage;
import org.springframework.web.multipart.MultipartFile;

public interface ComponentService {
    /**
     * Saves content of a given CSV file into the DB 
     * @param file the CSV file to be saved 
     */
    public void readCSV(MultipartFile multipartFile);
    /**
     * Gets the component with matching name
     * @param name the name of the component
     * @return the component
     */
    public Component getComponentByName(String name);
    /**
     * Gets all components
     * @return a list of all components
     */
    public List<Component> getComponents();
    /**
     * Gets all components of given type
     * @type the type of the components
     * @return a list of all components of given type
     */
    public List<Component> getComponentsOfType(String type);
    /**
     * gets the component with matching id
     * @param id the id of the component
     * @return the component
     */
    public Component getComponentById(int id);

    void handle(RabbitMessage message);
}
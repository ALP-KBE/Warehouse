package ALP.KBEWarehouse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ALP.KBEWarehouse.RabbitMQ.RabbitMQSender;
import ALP.RabbitMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ComponentServiceImplementation implements ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    RabbitMQSender warehouseSender;

    @Autowired
    Gson gson;

    @Override
    public void readCSV(MultipartFile file) {
        List<Component> components = null;
        try {
            components = CSVParser.parse(new FileReader(file.getOriginalFilename()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        componentRepository.saveAll(components);
    }

    @Override
    public Component getComponentByName(String name) {
        List<Component> toReturn = List.copyOf(parseIterableToList(componentRepository.findAll()));
        return toReturn.stream()
                .filter(comp -> comp.getName().equals(name))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public List<Component> getComponents() {
        return parseIterableToList(componentRepository.findAll());
    }

    @Override
    public List<Component> getComponentsOfType(String type) {
        List<Component> toReturn = List.copyOf(parseIterableToList(componentRepository.findAll()));
        return toReturn.stream()
                .filter(comp -> comp.getKomponententyp().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public Component getComponentById(int id) {
        List<Component> toReturn = List.copyOf(parseIterableToList(componentRepository.findAll()));
        try {
            return toReturn.stream()
                    .collect(Collectors.toList())
                    .get(id);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    @Override
    public void handle(RabbitMessage message) {
        if (message.getType().equals("getComponents"))
            if (message.getValue().equals("")) {
                List<Component> components = getComponents();
                warehouseSender.send(new RabbitMessage("component", gson.toJson(components)));
            } else {
                Component components = getComponentById(Integer.valueOf((String) message.getValue()));
                if (components != null) {
                    warehouseSender.send(new RabbitMessage("component", gson.toJson(components)));
                } else {
                    warehouseSender.send(new RabbitMessage("component", "IndexOutOfBoundsExceptionOops"));
                }
            }
    }

    /**
     * creates a list from a iterable. Usefull for parsing of componentsRepository
     * 
     * @param toParse the iterable to parse
     * @return a list representation of the iterable
     */
    private List<Component> parseIterableToList(Iterable<Component> toParse) {
        List<Component> components = new LinkedList<>();
        toParse.forEach(component -> components.add(component));
        return components;
    }
}
package ALP.KBEWarehouse;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ALP.KBEWarehouse.RabbitMQ.RabbitMQSender;
import ALP.RabbitMessage;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ComponentServiceImplementation implements ComponentService {

    @Autowired
    ComponentRepository componentRepository;

    @Autowired
    RabbitMQSender warehouseSender;

    @Autowired
    Gson gson;

    @Value("classpath:components.csv")
    private Resource componentsPath;

    @PostConstruct
    public void init()  {
        componentRepository.deleteAll();
        System.out.println("Datenbank geleert");
        InputStream componentsStream = null;
        try {
            componentsStream = componentsPath.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.readCSV(componentsStream);
        System.out.println("Datenbank gefuellt");
    }

    @Override
    public void readCSV(InputStream inputStream) {
        List<Component> components = null;
        components = CSVParser.parse(new InputStreamReader(inputStream));
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
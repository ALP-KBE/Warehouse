package ALP.KBEWarehouse;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends CrudRepository<Component, Integer> {

}
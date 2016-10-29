package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.Device;

import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<Device,UUID> {
}

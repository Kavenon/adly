package pl.edu.agh.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.student.model.Device;
import pl.edu.agh.student.model.VendorAndProfileKey;
import pl.edu.agh.student.model.VendorProfiles;

import java.util.UUID;

@Repository
public interface VendorProfilesRepository extends CrudRepository<VendorProfiles,VendorAndProfileKey> {
}

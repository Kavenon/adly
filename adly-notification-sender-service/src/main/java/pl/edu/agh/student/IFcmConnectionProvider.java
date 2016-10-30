package pl.edu.agh.student;

import java.util.Optional;

public interface IFcmConnectionProvider {
    Optional<FcmConnection> getFcmConnection();
}

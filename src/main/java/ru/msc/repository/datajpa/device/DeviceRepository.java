package ru.msc.repository.datajpa.device;

import ru.msc.model.Device;

import java.util.List;

public interface DeviceRepository {

    Device save(Device device);

    boolean delete(int id);

    Device get(int id);

    List<Device> getAll();

    Device getWithActiveProblem(int id);
}

package ru.msc.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.msc.model.Device;
import ru.msc.repository.datajpa.device.DeviceRepository;

import java.util.List;

import static ru.msc.util.validation.ValidationUtil.checkNotFoundWithId;

@Service
public class DeviceService {

    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public Device get(int deviceId) {
        return checkNotFoundWithId(repository.get(deviceId), deviceId);
    }

    public List<Device> getAll() {
        return repository.getAll();
    }

    public Device getWithActiveProblem(int deviceId) {
        return checkNotFoundWithId(repository.getWithActiveProblem(deviceId), deviceId);
    }
//    public List<Restaurant> getAllWithMenu() {
//        return repository.getAllWithMenu(LocalDate.now().atStartOfDay(),
//                LocalDate.now().plusDays(1).atStartOfDay());
//    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(Device device) {
        Assert.notNull(device, "device must not be null");
        checkNotFoundWithId(repository.save(device), device.id());
    }

    public Device create(Device device) {
        Assert.notNull(device, "device must not be null");
        return repository.save(device);
    }
}
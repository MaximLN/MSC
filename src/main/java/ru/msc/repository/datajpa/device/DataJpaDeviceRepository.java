package ru.msc.repository.datajpa.device;

import org.springframework.stereotype.Repository;
import ru.msc.model.Device;

import java.util.List;

@Repository
public class DataJpaDeviceRepository implements DeviceRepository {

    private final CrudDeviceRepository crudDeviceRepository;

    public DataJpaDeviceRepository(CrudDeviceRepository crudDeviceRepository) {
        this.crudDeviceRepository = crudDeviceRepository;
    }

    @Override
    public Device save(Device device) {
        return crudDeviceRepository.save(device);
    }

    @Override
    public boolean delete(int id) {
        return crudDeviceRepository.delete(id) != 0;
    }

    @Override
    public Device get(int id) {
        return crudDeviceRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Device> getAll() {
        return crudDeviceRepository.getAll();
    }

    @Override
    public Device getWithActiveProblem(int id) {
        return crudDeviceRepository.getWithActiveProblems(id);
    }

//    @Override
//    public List<Restaurant> getAllWithMenu(LocalDateTime todayDate, LocalDateTime tomorrowDate) {
//        return crudRestaurantRepository.getAllWithMenu(todayDate, tomorrowDate);
//    }
}

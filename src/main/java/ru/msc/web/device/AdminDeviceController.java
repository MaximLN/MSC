package ru.msc.web.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.msc.View;
import ru.msc.model.Device;
import ru.msc.service.DeviceService;
import ru.msc.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.msc.util.validation.ValidationUtil.assureIdConsistent;
import static ru.msc.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = "/admin/device", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDeviceController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DeviceService deviceService;

//    @DeleteMapping("/{deviceId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int deviceId) {
//        int userId = SecurityUtil.authUserId();
//        deviceService.delete(userId, deviceId);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Device> createWithLocation(@Validated(View.Web.class) @RequestBody Device device) {
//        checkNew(device);
//        Device created = deviceService.create(device);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/rest/profile/device" + "/{deviceId}")
//                .buildAndExpand(created.getId()).toUri();
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
//
//    @PutMapping(value = "/{deviceId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@Validated(View.Web.class) @RequestBody Device device, @PathVariable int deviceId) {
//        assureIdConsistent(device, deviceId);
//        deviceService.update(device);
//    }

    @GetMapping
    public List<Device> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return deviceService.getAll();
    }

    @GetMapping("/{id}")
    public Device get(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get device {} for user {}", id, userId);
        return deviceService.get(id);
    }


}
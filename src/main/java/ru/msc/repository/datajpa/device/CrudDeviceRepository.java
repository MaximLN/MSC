package ru.msc.repository.datajpa.device;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.msc.model.Device;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDeviceRepository extends JpaRepository<Device, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Device d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT d FROM Device d ORDER BY d.description DESC")
    List<Device> getAll();

    @EntityGraph(attributePaths = {"problems"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM Device d JOIN d.problems p WHERE d.id=?1 AND p.active>=?2")
    Device getWithActiveProblems(int id);

//    @EntityGraph(attributePaths = {"menuItems"}, type = EntityGraph.EntityGraphType.LOAD)
//    @Query("SELECT r FROM Restaurant r JOIN r.menuItems m WHERE m.dateTime>=?1 AND m.dateTime <?2 ORDER BY r.description DESC")
//    List<Restaurant> getAllWithMenu(LocalDateTime todayDate, LocalDateTime tomorrowDate);
}

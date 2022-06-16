package ru.msc.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.msc.View;
import ru.msc.util.validation.NoHtml;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Device.DELETE, query = "DELETE FROM Device d WHERE d.id=:id"),
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "devices", uniqueConstraints = {@UniqueConstraint(columnNames = {"description"})})
public class Device extends AbstractBaseEntity {
    public static final String DELETE = "Device.delete";

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 2, max = 120)
    @NoHtml(groups = {View.Web.class})
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "device")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    private List<Problem> problems;

    public Device() {
    }

    public Device(Integer id, String description) {
        super(id);
        this.description = description;
    }

    public Device(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    @Override
    public String toString() {
        return "Device{" +
                " description='" + description + '\'' +
                '}';
    }
}

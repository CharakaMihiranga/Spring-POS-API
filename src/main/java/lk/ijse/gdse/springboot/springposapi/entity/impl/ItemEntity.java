package lk.ijse.gdse.springboot.springposapi.entity.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lk.ijse.gdse.springboot.springposapi.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class ItemEntity implements SuperEntity {

    @Id
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "LONGTEXT")
    private String itemPic;

    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int qtyOnHand;

    @JsonIgnore
    @Column(updatable = false)
    private LocalDate createdAt;

    @JsonIgnore
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "itemEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetailEntity> orderDetailEntities;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }
}

package lk.ijse.gdse.springboot.springposapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_details")
public class OrderDetailEntity implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "item_code", referencedColumnName = "code", nullable = false)
    private ItemEntity itemEntity;

    @Column(nullable = false)
    private int qty;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;
}

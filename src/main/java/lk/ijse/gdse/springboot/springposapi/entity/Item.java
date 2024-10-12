package lk.ijse.gdse.springboot.springposapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table( name = "item")
public class Item implements SuperEntity{
   @Id
   private String code;
   private String name;
   private String description;
   private double price;
   private int qtyOnHand;
   private LocalDate createdAt;
   private LocalDate updatedAt;
   @OneToMany
   private List <OrderDetails> orderDetails;
   @PrePersist
    protected void onCreate() {
         this.createdAt = LocalDate.now();
    }
   @PreUpdate
    protected void onUpdate() {
         this.updatedAt = LocalDate.now();
    }

}

package lk.ijse.gdse.springboot.springposapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ItemEntity implements SuperEntity{
   @Id
   private String code;
   private String name;
   @Column(columnDefinition = "LONGTEXT")
   private String itemPic;
   private String description;
   private double price;
   private int qtyOnHand;
   private LocalDate createdAt;
   private LocalDate updatedAt;
   @OneToMany
   @JsonIgnore
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

package com.deepak.ecommerce_backend.entity;

import com.deepak.ecommerce_backend.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "orders",
        indexes = {
        @Index( name = "idx_order_user_id", columnList = "user_id"),
                @Index( name = "idx_order_public_id", columnList = "public_id")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Order extends BaseEntity{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "public_id", nullable = false, unique = true, updatable = false)
    private UUID publicId;

    @NotNull(message = "Amount is required")
    @Min(value = 0)
    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;

    @NotBlank(message = "Address is Required")
    @Column(name = "shipping_address", nullable = false, length = 200)
    private String shippingAddress;

    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;

    @PrePersist
    protected void onCreate(){
        if(this.publicId == null){
            this.publicId = UUID.randomUUID();
        }
    }

}
